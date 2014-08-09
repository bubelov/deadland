package com.deadland;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.deadland.model.Entity;

/**
 * Created by inver on 09.08.2014.
 */
public class ControlManager {
    public static ControlManager instance = new ControlManager();

    public OrthographicCamera camera;
    public Entity isUnderConstruction = null;
    public boolean justLeftConstruction = false;
}
