package com.deadland.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.deadland.Constants;

/**
 * @author Alexey Nevinsky
 * @version 20.08.14 22:16
 */
public class WorldRenderer {

    private DeadlandWorld world;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    public WorldRenderer(DeadlandWorld world) {
        this.world = world;

        batch = new SpriteBatch();

        camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        world.getScene().render(batch);

        batch.end();

        System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond());
    }

    public void debugRender() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(world.getWorld(), camera.combined);

        System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond());
    }
}
