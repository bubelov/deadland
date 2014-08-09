package com.deadland.model.menu.wall;

import com.badlogic.gdx.graphics.Texture;
import com.deadland.model.building.Building;
import com.deadland.model.building.Wall;
import com.deadland.model.menu.BuildingSpirit;
import com.deadland.model.menu.MenuButton;

/**
 * Created by inver on 10.08.2014.
 */
public class WallSpirit extends BuildingSpirit {
    public static Texture texture = new Texture("building_wall_transparent.png");

    public WallSpirit(float x, float y, MenuButton e) {
        super(x, y, e);
    }

    @Override
    protected Texture getTexture() {
        return texture;
    }

    @Override
    protected Building createBuilding(float x, float y) {
        return new Wall(x - 8, y - 8, 300);
    }
}
