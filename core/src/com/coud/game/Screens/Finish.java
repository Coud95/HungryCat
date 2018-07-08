package com.coud.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.coud.game.Game;

public class Finish implements Screen {
    private OrthographicCamera camera;
    private final Game game;
    private static Texture catTexture;
    private static Music finishMusic;

    Finish(final Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        Game.backgroundMusic.stop();
        finishMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/finish.mp3"));
        finishMusic.play();
        Game.backgroundTexture = new Texture("background.jpg");
        Game.backgroundSprite = new Sprite(Game.backgroundTexture);
        catTexture = new Texture("cat_finish.png");
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
        game.font.draw(game.batch, "You finished the game :)", 330, 280);
        game.font.draw(game.batch, "     Congratulations!   ", 330, 250);
        game.font.draw(game.batch, "    Created by: Coud    ", 330, 220);
        game.batch.draw(catTexture, 365, 120);
        game.batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
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
        catTexture.dispose();
        finishMusic.dispose();
    }
}
