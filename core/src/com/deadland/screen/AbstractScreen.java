package com.deadland.screen;

import com.badlogic.gdx.Game;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public abstract class AbstractScreen extends ScreenAdapter {
    private Game game;

    public AbstractScreen(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
