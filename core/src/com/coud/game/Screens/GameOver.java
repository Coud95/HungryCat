package com.coud.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.coud.game.Game;

public class GameOver implements Screen {
    private OrthographicCamera camera;
    private final Game game;
    private Music gameOverMusic;
    private int level;
    private static Texture catTexture;

    public GameOver(final Game game, int level) {
        this.game = game;
        this.level = level;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        Game.backgroundMusic.pause();
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/gameover.mp3"));
        gameOverMusic.play();
        Game.backgroundTexture = new Texture("background.jpg");
        Game.backgroundSprite = new Sprite(Game.backgroundTexture);
        catTexture = new Texture("cat_gameover.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        Game.backgroundSprite.draw(game.batch);
        game.font.draw(game.batch, "     GAME OVER      ", 330, 280);
        game.font.draw(game.batch, "Press R to try again", 330, 250);
        game.font.draw(game.batch, "      ESC to exit   ", 330, 220);
        game.batch.draw(catTexture, 365, 120);
        game.batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            switch (level) {
                case 1: {
                    game.setScreen(new Level1(game));
                    Game.freshSpawn();
                    break;
                }
                case 2: {
                    game.setScreen(new Level2(game));
                    Game.freshSpawn();
                    break;
                }
                case 3: {
                    game.setScreen(new Level3(game));
                    Game.freshSpawn();
                    break;
                }
            }
            dispose();
        } else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
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
        gameOverMusic.dispose();
    }
}
