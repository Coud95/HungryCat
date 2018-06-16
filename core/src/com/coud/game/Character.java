package com.coud.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Character extends Rectangle {
    public static final Texture TEXTURE = new Texture(("cat.png"));
    private boolean flipped = false;

    public Character(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void movement(Sprite characterSprite) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                x -= 600 * Gdx.graphics.getDeltaTime();
            } else {
                x -= 300 * Gdx.graphics.getDeltaTime();
            }
            if (!flipped) characterSprite.flip(true, false);
            flipped = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                x += 600 * Gdx.graphics.getDeltaTime();
            } else {
                x += 300 * Gdx.graphics.getDeltaTime();
            }
            if (flipped) characterSprite.flip(true, false);
            flipped = false;
        }
        if (x < 0) x = 0;
        if (x > 800 - 64) x = 800 - 64;
    }
}
