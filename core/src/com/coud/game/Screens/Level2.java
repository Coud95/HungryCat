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
import com.coud.game.Items.Bomb;
import com.coud.game.Items.Cupcake;
import com.coud.game.Items.Fruit;
import com.coud.game.Game;
import com.coud.game.LevelDefaults;

import java.util.Iterator;

import static com.badlogic.gdx.math.MathUtils.random;

public class Level2 implements Screen, LevelDefaults {
    private final Game game;
    private long lastFruitDropTime;
    private long lastBombDropTime;
    private long lastCupcakeDropTime;
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
        lastFruitDropTime = TimeUtils.millis();
        lastBombDropTime = TimeUtils.millis();
        lastCupcakeDropTime = TimeUtils.millis();
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
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        Game.backgroundSprite.draw(batch);
        batch.draw(Game.characterSprite, Game.player.x, Game.player.y);
        drawItems(Game.fruits, Game.bombs, Game.cupcakes, batch);
        scoreBitmapFont.setColor(1, 1, 1, 1);
        scoreBitmapFont.draw(batch, Game.eatLabel, 25, 460);
        starting(level2sprite, batch, started);
        batch.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) started = true;
        if (started) {
            Game.player.movement(Game.characterSprite);
            if (TimeUtils.millis() - lastFruitDropTime > 1000) {
                Game.fruits.add(new Fruit(random(0, 800 - 32), 480, 32, 32, random.nextInt(3 - 1 + 1) + 1, random.nextInt((410 - 330) + 1) + 330));
                lastFruitDropTime = TimeUtils.millis();
            }
            if (TimeUtils.millis() - lastBombDropTime > random.nextInt((3000 - 2000) + 1) + 2000) {
                Game.bombs.add(new Bomb(random(0, 800 - 32), 480, 32, 32));
                lastBombDropTime = TimeUtils.millis();
            }
            if (TimeUtils.millis() - lastCupcakeDropTime > random.nextInt((8000 - 5000) + 1) + 5000) {
                Game.cupcakes.add(new Cupcake(random(0, 800 - 32), 480, 32, 32, random.nextInt((500 - 450) + 1) + 450));
                lastCupcakeDropTime = TimeUtils.millis();
            }
            for (Iterator<Fruit> iter = Game.fruits.iterator(); iter.hasNext(); ) {
                Fruit fruit = iter.next();
                fruit.y -= fruit.speed * Gdx.graphics.getDeltaTime();
                fruitMechanics(fruit, iter);
            }
            bombMechanics(Game.bombs, 2, game);
            for (Iterator<Cupcake> iter = Game.cupcakes.iterator(); iter.hasNext(); ) {
                Cupcake cupcake = iter.next();
                cupcake.y -= cupcake.speed * Gdx.graphics.getDeltaTime();
                cupcakeMechanics(cupcake, iter);
            }
            if (Game.eat <= 0) {
                Game.freshSpawn();
                game.setScreen(new Level3(game));
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
        for (Fruit fruit : Game.fruits) {
            fruit.APPLE.dispose();
            fruit.BANANA.dispose();
            fruit.GRAPES.dispose();
        }
        Bomb.BOMB.dispose();
        Cupcake.CUPCAKE.dispose();
    }
}
