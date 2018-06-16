package com.coud.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.coud.game.Screens.Menu;

public class Game extends com.badlogic.gdx.Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public static Music backgroundMusic;


    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/background.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        this.setScreen(new Menu(this));
    }


    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        backgroundMusic.dispose();
    }
}
