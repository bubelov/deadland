package com.deadland.model.menu.gun_tower;

import com.badlogic.gdx.graphics.Texture;
import com.deadland.model.menu.BuildingSpirit;
import com.deadland.model.menu.MenuButton;

/**
 * Created by inver on 10.08.2014.
 */
public class GunTowerButton extends MenuButton {
    public static Texture texture = new Texture("menu_gunTower.png");

    public GunTowerButton(float x, float y) {
        super(x, y);
    }

    @Override
    protected Texture getTexture() {
        return texture;
    }

    @Override
    protected BuildingSpirit createSpirit(float x, float y) {
        return new GunTowerSpirit(x, y, this);
    }
}
