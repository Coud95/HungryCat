package com.coud.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.coud.game.Items.Fruit;
import com.coud.game.Game;
import com.coud.game.LevelDefaults;

import java.util.Iterator;

import static com.badlogic.gdx.math.MathUtils.random;

public class Level1 implements Screen, LevelDefaults {
    private final Game game;
    private long lastFruitDropTime;
    private OrthographicCamera camera;
    private static SpriteBatch batch;
    private static BitmapFont scoreBitmapFont;
    private static Texture level1;
    private static Sprite level1Sprite;
    private boolean started = false;


    Level1(final Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        Game.backgroundTexture = new Texture("level1.jpg");
        Game.backgroundSprite = new Sprite(Game.backgroundTexture);
        level1 = new Texture("1.png");
        level1Sprite = new Sprite(level1);
        batch = new SpriteBatch();
        lastFruitDropTime = TimeUtils.nanoTime();
        Game.eat = 50;
        Game.eatLabel = "eat: 50";
        scoreBitmapFont = new BitmapFont();
        checkBackgroundMusic();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        Game.backgroundSprite.draw(batch);
        batch.draw(Game.characterSprite, Game.player.x, Game.player.y);
        drawItems(Game.fruits, Game.bombs, Game.cupcakes, batch);
        scoreBitmapFont.setColor(1, 1, 1, 1);
        scoreBitmapFont.draw(batch, Game.eatLabel, 25, 460);
        starting(level1Sprite, batch, started);
        batch.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) started = true;
        if (started) {
            Game.player.movement(Game.characterSprite);
            if (TimeUtils.nanoTime() - lastFruitDropTime > 1000000000) {
                Game.fruits.add(new Fruit(random(0, 800 - 32), 480, 32, 32, random.nextInt(3 - 1 + 1) + 1, random.nextInt((410 - 330) + 1) + 330));
                lastFruitDropTime = TimeUtils.nanoTime();
            }
            for (Iterator<Fruit> iter = Game.fruits.iterator(); iter.hasNext(); ) {
                Fruit fruit = iter.next();
                fruit.y -= fruit.speed * Gdx.graphics.getDeltaTime();
                fruitMechanics(fruit, iter);
            }
            if (Game.eat <= 0) {
                Game.freshSpawn();
                game.setScreen(new Level2(game));
            } else if (Game.eat > 55) {
                game.setScreen(new GameOver(game, 1));
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
        for (Fruit fruit : Game.fruits) {
            fruit.APPLE.dispose();
            fruit.BANANA.dispose();
            fruit.GRAPES.dispose();
        }
        level1.dispose();
    }
}
