package com.deadland.base;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.deadland.ControlManager;
import com.deadland.EntityManager;

/**
 * @author Alexey Nevinsky
 * @version 24.08.14 0:27
 */
public class BaseGestureDetector extends GestureDetector.GestureAdapter {

    private Camera camera;

    public BaseGestureDetector(Camera camera) {
        this.camera = camera;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Vector3 position = new Vector3(x, y, 0);
        camera.unproject(position);
        ControlManager.instance.demoScene.onTap(position.x, position.y, count, button);
        EntityManager.instance.onTap(position.x, position.y, count, button);
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX, deltaY, 0);
        camera.update();
        return false;
    }
}
