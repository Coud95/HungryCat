package com.coud.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.coud.game.Items.Bomb;
import com.coud.game.Items.Cupcake;
import com.coud.game.Items.Fruit;
import com.coud.game.Screens.Menu;

public class Game extends com.badlogic.gdx.Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public static Music backgroundMusic;
    static Sound biteSound;
    static Sound failSound;
    static Sound bombSound;
    public static Character player;
    public static Sprite characterSprite;
    public static int eat;
    public static String eatLabel;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    public static Array<Fruit> fruits;
    public static Array<Bomb> bombs;
    public static Array<Cupcake> cupcakes;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/background.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        biteSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bite.mp3"));
        failSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fail.wav"));
        bombSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bomb.mp3"));
        player = new Character(800 / 2 - 64 / 2, 55, 64, 64);
        characterSprite = new Sprite(Character.TEXTURE);
        freshSpawn();
        this.setScreen(new Menu(this));
    }

    public static void freshSpawn() {
        fruits = new Array<>();
        bombs = new Array<>();
        cupcakes = new Array<>();
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
        bombSound.dispose();
        Character.TEXTURE.dispose();
    }
}
