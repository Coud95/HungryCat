package com.coud.game;

import com.coud.game.Items.Fruit;

import java.util.Iterator;

public interface LevelDefaults {

    default void checkBackgroundMusic() {
        if(!Game.backgroundMusic.isPlaying()) {
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
}
