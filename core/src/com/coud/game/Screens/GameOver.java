package com.coud.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.coud.game.Game;

public class GameOver implements Screen {
    private OrthographicCamera camera;
    private final Game game;
    private Music gameOverMusic;
    private int level;

    public GameOver(final Game game, int level) {
        this.game = game;
        this.level = level;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        Game.backgroundMusic.pause();
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/gameover.wav"));
        gameOverMusic.play();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.font.draw(game.batch, "GAME OVER!!! ", 400, 150);
        game.font.draw(game.batch, "Press R to try again!", 400, 100);
        game.font.draw(game.batch, "ESC to exit!", 400, 50);
        game.batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            switch (level) {
                case 1: {
                    game.setScreen(new Level1(game));
                    break;
                }
                case 2:
                    game.setScreen(new Level2(game));
                    break;
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
