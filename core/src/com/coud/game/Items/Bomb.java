package com.coud.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bomb extends Rectangle {
    public static final Texture BOMB = new Texture(Gdx.files.internal("drop/bomb.png"));
    public final int speed = 450;

    public Bomb(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
}
