package com.deadland.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class WorldMapScreen extends AbstractScreen {
    public WorldMapScreen(Game game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // To move on different screen
        //getGame().setScreen(new BlaBlaScreen(this));
    }
}
