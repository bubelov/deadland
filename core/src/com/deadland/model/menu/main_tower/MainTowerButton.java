package com.deadland.model.menu.main_tower;

import com.badlogic.gdx.graphics.Texture;
import com.deadland.model.menu.BuildingSpirit;
import com.deadland.model.menu.MenuButton;

/**
 * Created by inver on 10.08.2014.
 */
public class MainTowerButton extends MenuButton {
    public static Texture texture = new Texture("menu_mainTower.png");

    public MainTowerButton(float x, float y) {
        super(x, y);
    }

    @Override
    protected Texture getTexture() {
        return texture;
    }

    @Override
    protected BuildingSpirit createSpirit(float x, float y) {
        return new MainTowerSpirit(x, y, this);
    }
}
