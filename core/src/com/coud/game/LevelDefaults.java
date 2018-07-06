package com.coud.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.coud.game.Items.Bomb;
import com.coud.game.Items.Cupcake;
import com.coud.game.Items.Fruit;
import com.coud.game.Screens.GameOver;

import java.util.Iterator;

public interface LevelDefaults {

    default void checkBackgroundMusic() {
        if (!Game.backgroundMusic.isPlaying()) {
            Game.backgroundMusic.play();
        }
    }

    default void fruitMechanics(Fruit fruit, Iterator<Fruit> iter) {
        if (fruit.y + 64 < 0) {
            iter.remove();
            Game.eat += 5;
            Game.eatLabel = "eat: " + Game.eat;
            Game.failSound.play();
        }
        if (fruit.overlaps(Game.player)) {
            Game.eat--;
            Game.eatLabel = "eat: " + Game.eat;
            Game.biteSound.play();
            iter.remove();
        }
    }

    default void cupcakeMechanics(Cupcake cupcake, Iterator<Cupcake> iter) {
        if (cupcake.y + 64 < 0) {
            iter.remove();
        }
        if (cupcake.overlaps(Game.player)) {
            Game.eat -= 10;
            Game.eatLabel = "eat: " + Game.eat;
            Game.biteSound.play();
            iter.remove();
        }
    }

    default void bombMechanics(Array<Bomb> bombs, int level, Game game) {
        for (Bomb bomb : bombs) {
            bomb.y -= bomb.speed * Gdx.graphics.getDeltaTime();
            if (bomb.overlaps(Game.player)) {
                game.setScreen(new GameOver(game, level));
                Game.bombSound.play();
            }
        }
    }

    default void drawItems(Array<Fruit> fruits, Array<Bomb> bombs, Array<Cupcake> cupcakes, Batch batch) {
        for (Fruit fruit : fruits) {
            batch.draw(fruit.randomFruit(), fruit.x, fruit.y);
        }
        for (Bomb bomb : bombs) {
            batch.draw(new Sprite(Bomb.BOMB), bomb.x, bomb.y);
        }
        for (Cupcake cupcake : cupcakes) {
            batch.draw(new Sprite(Cupcake.CUPCAKE), cupcake.x, cupcake.y);
        }
    }

    default void starting(Sprite text, Batch batch, boolean started) {
        if (!started) {
            batch.draw(text, 0, 0);
        }
    }
}
