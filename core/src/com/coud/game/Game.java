package com.coud.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import static com.badlogic.gdx.math.MathUtils.random;

public class Game extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Character player;
    private Sprite characterSprite;
    private Music backgroundMusic;
    private Sound biteSound;
    private Array<Fruit> fruits;
    private long lastFruitDropTime;
    private int score = 0;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        player = new Character(800 / 2 - 64 / 2, 20, 64, 64);

        characterSprite = new Sprite(player.TEXTURE);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
        biteSound = Gdx.audio.newSound(Gdx.files.internal("bite.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        fruits = new Array<Fruit>();
        Fruit fruit = new Fruit(random(0, 800 - 64), 480, 32, 32, random.nextInt(3 - 1 + 1) + 1);
        lastFruitDropTime = TimeUtils.nanoTime();
        fruits.add(fruit);
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(characterSprite, player.x, player.y);
        for (Fruit fruit : fruits) {
            batch.draw(fruit.randomFruit(), fruit.x, fruit.y);
        }
        batch.end();
        player.movement(characterSprite);
        if (TimeUtils.nanoTime() - lastFruitDropTime > 1000000000) {
            fruits.add(new Fruit(random(0, 800 - 64), 480, 64, 64, random.nextInt(3 - 1 + 1) + 1));
            lastFruitDropTime = TimeUtils.nanoTime();
        }
        for (Iterator<Fruit> iter = fruits.iterator(); iter.hasNext(); ) {
            Fruit fruit = iter.next();
            fruit.y -= 300 * Gdx.graphics.getDeltaTime();
            if (fruit.y + 64 < 0) iter.remove();
            if (fruit.overlaps(player)) {
                score += 1;
                System.out.println("Punkty: " + score);
                biteSound.play();
                iter.remove();
            }
        }
    }

    @Override
    public void dispose() {
        for (Fruit fruit : fruits) {
            fruit.APPLE.dispose();
            fruit.BANANA.dispose();
            fruit.GRAPES.dispose();
        }
        backgroundMusic.dispose();
        biteSound.dispose();
    }
}
