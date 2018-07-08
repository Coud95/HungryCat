package com.coud.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.coud.game.Game;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "HungryCat";
        config.width = 800;
        config.height = 480;
        config.resizable = false;
        config.addIcon("cat.png", Files.FileType.Internal);
        new LwjglApplication(new Game(), config);
    }
}
