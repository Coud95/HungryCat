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
import com.coud.game.Game;
import com.coud.game.Items.Bomb;
import com.coud.game.Items.Cupcake;
import com.coud.game.Items.Fruit;
import com.coud.game.LevelDefaults;

import java.util.Iterator;

import static com.badlogic.gdx.math.MathUtils.random;

public class Level3 implements Screen, LevelDefaults {
    private final Game game;
    private long lastCupcakeDropTime;
    private long lastBombDropTime;
    private OrthographicCamera camera;
    private static SpriteBatch batch;
    private static BitmapFont scoreBitmapFont;
    private static Texture level3;
    private static Sprite level3sprite;
    private boolean started = false;

    Level3(final Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        Game.backgroundTexture = new Texture("level3.png");
        Game.backgroundSprite = new Sprite(Game.backgroundTexture);
        level3 = new Texture("3.png");
        level3sprite = new Sprite(level3);
        batch = new SpriteBatch();
        lastCupcakeDropTime = TimeUtils.millis();
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
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        Game.backgroundSprite.draw(batch);
        batch.draw(Game.characterSprite, Game.player.x, Game.player.y);
        drawItems(Game.fruits, Game.bombs, Game.cupcakes, batch);
        scoreBitmapFont.setColor(1, 1, 1, 1);
        scoreBitmapFont.draw(batch, Game.eatLabel, 25, 460);
        starting(level3sprite, batch, started);
        batch.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) started = true;
        if (started) {
            Game.player.movement(Game.characterSprite);
            if (TimeUtils.millis() - lastCupcakeDropTime > 5000) {
                Game.cupcakes.add(new Cupcake(random(0, 800 - 32), 480, 32, 32, random.nextInt((500 - 450) + 1) + 450));
                lastCupcakeDropTime = TimeUtils.millis();
            }
            if (TimeUtils.millis() - lastBombDropTime > 300) {
                Game.bombs.add(new Bomb(random(0, 800 - 32), 480, 32, 32));
                lastBombDropTime = TimeUtils.millis();
            }
            bombMechanics(Game.bombs, 3, game);
            for (Iterator<Cupcake> iter = Game.cupcakes.iterator(); iter.hasNext(); ) {
                Cupcake cupcake = iter.next();
                cupcake.y -= cupcake.speed * Gdx.graphics.getDeltaTime();
                cupcakeMechanics(cupcake, iter);
            }
            if (Game.eat <= 0) {
                game.setScreen(new Finish(game));
            } else if (Game.eat > 110) {
                game.setScreen(new GameOver(game, 3));
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
        level3.dispose();
    }
}
