package com.deadland.model.menu.main_tower;

import com.badlogic.gdx.graphics.Texture;
import com.deadland.model.building.Building;
import com.deadland.model.building.MainTower;
import com.deadland.model.menu.BuildingSpirit;
import com.deadland.model.menu.MenuButton;

/**
 * Created by inver on 10.08.2014.
 */
public class MainTowerSpirit extends BuildingSpirit {
    public static Texture texture = new Texture("building_mainTower_transparent.png");

    public MainTowerSpirit(float x, float y, MenuButton e) {
        super(x, y, e);
    }

    @Override
    protected Texture getTexture() {
        return texture;
    }

    @Override
    protected Building createBuilding(float x, float y) {
        return new MainTower(x - 16, y - 16);
    }
}
