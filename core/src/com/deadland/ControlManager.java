package com.deadland;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.deadland.base.GameScene;
import com.deadland.base.model.Entity;
import com.deadland.model.menu.BuildingSpirit;

/**
 * Created by inver on 09.08.2014.
 */
public class ControlManager {
    public static ControlManager instance = new ControlManager();

    public OrthographicCamera camera;
    public GameScene demoScene;

    public Entity isUnderConstruction = null;
    public boolean justLeftConstruction = false;

    public BuildingSpirit spirit = null;
}
