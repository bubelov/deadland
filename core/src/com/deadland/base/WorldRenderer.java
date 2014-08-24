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

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Box2DDebugRenderer debugRenderer;

    private GameScene scene;

    public WorldRenderer() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        debugRenderer = new Box2DDebugRenderer();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void render(boolean isDebug) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (isDebug) {
            debugRenderer.render(((DemoScene) scene).getWorld(), batch.getProjectionMatrix().cpy().scl(1000));
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        float minX = camera.position.x - camera.viewportWidth / 2;
        float maxX = camera.position.x + camera.viewportWidth / 2;
        float minY = camera.position.y - camera.viewportHeight / 2;
        float maxY = camera.position.y + camera.viewportHeight / 2;
        scene.smartRender(batch, minX, maxY, maxX, minY);

        batch.end();

        System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond());
    }

    public void setScene(GameScene scene) {
        this.scene = scene;
    }
}
