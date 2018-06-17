package com.coud.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.coud.game.Screens.Menu;

public class Game extends com.badlogic.gdx.Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public static Music backgroundMusic;
    public static Sound biteSound;
    public static Sound failSound;
    public static Character player;
    public static Sprite characterSprite;



    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/background.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        biteSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bite.mp3"));
        failSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fail.wav"));
        player = new Character(800 / 2 - 64 / 2, 20, 64, 64);
        characterSprite = new Sprite(Character.TEXTURE);
        this.setScreen(new Menu(this));
    }


    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        backgroundMusic.dispose();
        biteSound.dispose();
        failSound.dispose();
        Character.TEXTURE.dispose();
    }
}
