package com.coud.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Cupcake extends Rectangle {
    public static final Texture CUPCAKE = new Texture(Gdx.files.internal("drop/cupcake.png"));
    public final int speed;

    public Cupcake(float x, float y, float width, float height, int speed) {
        super(x, y, width, height);
        this.speed = speed;
    }
}
