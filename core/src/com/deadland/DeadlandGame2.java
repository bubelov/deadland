package com.deadland;

import com.badlogic.gdx.Game;
import com.deadland.screen.WorldMapScreen;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class DeadlandGame2 extends Game {
    @Override
    public void create() {
        Assets.initialize();
        setScreen(new WorldMapScreen(this));
    }
}
