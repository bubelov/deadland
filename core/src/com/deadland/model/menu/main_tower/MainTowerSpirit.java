package com.deadland.model.menu.main_tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deadland.Assets;
import com.deadland.model.building.Building;
import com.deadland.model.building.MainTower;
import com.deadland.model.menu.BuildingSpirit;
import com.deadland.model.menu.MenuButton;

/**
 * Created by inver on 10.08.2014.
 */
public class MainTowerSpirit extends BuildingSpirit {
    public MainTowerSpirit(float x, float y, MenuButton e) {
        super(x, y, e);
    }

    @Override
    protected TextureRegion getTexture() {
        return Assets.Textures.buildingMainTowerTransparent;
    }

    @Override
    protected void setCollidesTexture() {

    }

    @Override
    protected void setNormalTexture() {

    }

    @Override
    protected Building createBuilding(float x, float y) {
        return new MainTower(x - 16, y - 16);
    }
}
