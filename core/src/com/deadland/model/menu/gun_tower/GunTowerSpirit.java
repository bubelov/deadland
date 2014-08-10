package com.deadland.model.menu.gun_tower;

import com.badlogic.gdx.graphics.Texture;
import com.deadland.model.building.Building;
import com.deadland.model.building.GunTower;
import com.deadland.model.menu.BuildingSpirit;
import com.deadland.model.menu.MenuButton;

/**
 * Created by inver on 09.08.2014.
 */
public class GunTowerSpirit extends BuildingSpirit {
    public static Texture greenTexture = new Texture("building_tower_green_transparent.png");
    public static Texture redTexture = new Texture("building_tower_red_transparent.png");

    public GunTowerSpirit(float x, float y, MenuButton e) {
        super(x, y, e);
    }

    @Override
    protected Texture getTexture() {
        return greenTexture;
    }

    @Override
    protected void setCollidesTexture() {
        sprite.setTexture(redTexture);
    }

    @Override
    protected void setNormalTexture() {
        sprite.setTexture(greenTexture);
    }

    @Override
    protected Building createBuilding(float x, float y) {
        return new GunTower(x - 16, y - 16);
    }
}
