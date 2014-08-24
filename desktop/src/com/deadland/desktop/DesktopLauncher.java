package com.deadland.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.deadland.Constants;
import com.deadland.DeadlandGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Constants.SCREEN_WIDTH / 2;
        config.height = Constants.SCREEN_HEIGHT / 2;
        config.title = "Deadland";
        config.samples = 16;
        new LwjglApplication(new DeadlandGame(), config);
    }
}
