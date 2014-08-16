package com.deadland.model.menu.gun_tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deadland.Assets;
import com.deadland.model.building.Building;
import com.deadland.model.building.GunTower;
import com.deadland.model.menu.BuildingSpirit;
import com.deadland.model.menu.MenuButton;

/**
 * Created by inver on 09.08.2014.
 */
public class GunTowerSpirit extends BuildingSpirit {
    public GunTowerSpirit(float x, float y, MenuButton e) {
        super(x, y, e);
    }

    @Override
    protected TextureRegion getTexture() {
        return Assets.Textures.buildingTowerGreenTransparent;
    }

    @Override
    protected void setCollidesTexture() {
        sprite.setRegion(Assets.Textures.buildingTowerRedTransparent);
    }

    @Override
    protected void setNormalTexture() {
        sprite.setRegion(Assets.Textures.buildingTowerGreenTransparent);
    }

    @Override
    protected Building createBuilding(float x, float y) {
        return new GunTower(x - 16, y - 16);
    }
}
