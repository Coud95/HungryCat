package com.coud.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Fruit extends Rectangle {
    public final Texture BANANA = new Texture(Gdx.files.internal("fruits/banana.png"));
    public final Texture APPLE = new Texture(Gdx.files.internal("fruits/apple.png"));
    public final Texture GRAPES = new Texture(Gdx.files.internal("fruits/grapes.png"));
    private int fruitTexture;

    public Fruit(float x, float y, float width, float height, int fruitTexture) {
        super(x, y, width, height);
        this.fruitTexture = fruitTexture;
    }

    public Texture randomFruit() {
        if (fruitTexture == 1) {
            return BANANA;
        } else if (fruitTexture == 2) {
            return APPLE;
        } else {
            return GRAPES;
        }
    }
}
