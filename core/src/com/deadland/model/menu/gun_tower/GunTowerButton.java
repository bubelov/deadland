package com.deadland.model.menu.gun_tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deadland.Assets;
import com.deadland.model.menu.BuildingSpirit;
import com.deadland.model.menu.MenuButton;

/**
 * Created by inver on 10.08.2014.
 */
public class GunTowerButton extends MenuButton {
    public GunTowerButton(float x, float y, String menuName) {
        super(x, y, menuName);
    }

    @Override
    protected TextureRegion getTexture() {
        return Assets.Textures.menuGunTower;
    }

    @Override
    protected BuildingSpirit createSpirit(float x, float y) {
        return new GunTowerSpirit(x, y, this);
    }
}
