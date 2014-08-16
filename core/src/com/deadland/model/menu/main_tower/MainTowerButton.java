package com.deadland.model.menu.main_tower;

import com.badlogic.gdx.graphics.Texture;
import com.deadland.Assets;
import com.deadland.model.menu.BuildingSpirit;
import com.deadland.model.menu.MenuButton;

/**
 * Created by inver on 10.08.2014.
 */
public class MainTowerButton extends MenuButton {
    public MainTowerButton(float x, float y, String menuName) {
        super(x, y, menuName);
    }

    @Override
    protected Texture getTexture() {
        return Assets.Textures.menuMainTower;
    }

    @Override
    protected BuildingSpirit createSpirit(float x, float y) {
        return new MainTowerSpirit(x, y, this);
    }
}
