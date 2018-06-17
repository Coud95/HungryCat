package com.coud.game;


public interface LevelDefaults {

    default void checkBackgroundMusic() {
        if(!Game.backgroundMusic.isPlaying()) {
            Game.backgroundMusic.play();
        }
    }
}
