package com.coud.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.coud.game.Items.Bomb;
import com.coud.game.Items.Fruit;
import com.coud.game.Game;
import com.coud.game.LevelDefaults;

import java.util.Iterator;

import static com.badlogic.gdx.math.MathUtils.random;

public class Level2 implements Screen, LevelDefaults {
    private final Game game;
    private long lastFruitDropTime;
    private long lastBombDropTime;
    private Array<Fruit> fruits;
    private Array<Bomb> bombs;
    private OrthographicCamera camera;
    private static SpriteBatch batch;
    private static BitmapFont scoreBitmapFont;
    private static Sprite level2sprite;
    private boolean started = false;

    Level2(final Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        Game.backgroundTexture = new Texture("level2.jpg");
        Game.backgroundSprite = new Sprite(Game.backgroundTexture);
        Texture level2 = new Texture("2.png");
        level2sprite = new Sprite(level2);
        batch = new SpriteBatch();
        fruits = new Array<>();
        bombs = new Array<>();
        lastFruitDropTime = TimeUtils.millis();
        lastBombDropTime = TimeUtils.millis();
        Game.eat = 100;
        Game.eatLabel = "eat: 100";
        scoreBitmapFont = new BitmapFont();
        checkBackgroundMusic();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        Game.backgroundSprite.draw(batch);
        batch.draw(Game.characterSprite, Game.player.x, Game.player.y);
        for (Fruit fruit : fruits) {
            batch.draw(fruit.randomFruit(), fruit.x, fruit.y);
        }
        for (Bomb bomb : bombs) {
            batch.draw(new Sprite(Bomb.BOMB), bomb.x, bomb.y);
        }
        scoreBitmapFont.setColor(1, 1, 1, 1);
        scoreBitmapFont.draw(batch, Game.eatLabel, 25, 460);
        starting(level2sprite, batch, started);
        batch.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) started = true;
        if (started) {
            Game.player.movement(Game.characterSprite);
            if (TimeUtils.millis() - lastFruitDropTime > 1000) {
                fruits.add(new Fruit(random(0, 800 - 32), 480, 32, 32, random.nextInt(3 - 1 + 1) + 1));
                lastFruitDropTime = TimeUtils.millis();
            }
            if (TimeUtils.millis() - lastBombDropTime > 5000) {
                bombs.add(new Bomb(random(0, 800 - 32), 480, 32, 32));
                lastBombDropTime = TimeUtils.millis();
            }
            for (Iterator<Fruit> iter = fruits.iterator(); iter.hasNext(); ) {
                Fruit fruit = iter.next();
                fruit.y -= 400 * Gdx.graphics.getDeltaTime();
                fruitMechanics(fruit, iter);
            }
            for (Bomb bomb : bombs) {
                bomb.y -= 450 * Gdx.graphics.getDeltaTime();
                if (bomb.overlaps(Game.player)) {
                    game.setScreen(new GameOver(game, 2));
                    Game.bombSound.play();
                }
            }
            if (Game.eat <= 0) {
                game.setScreen(new GameOver(game, 2));
            } else if (Game.eat > 110) {
                game.setScreen(new GameOver(game, 2));
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for (Fruit fruit : fruits) {
            fruit.APPLE.dispose();
            fruit.BANANA.dispose();
            fruit.GRAPES.dispose();
        }
    }
}
