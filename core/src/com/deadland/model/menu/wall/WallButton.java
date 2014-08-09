package com.deadland.model.menu.wall;

import com.badlogic.gdx.graphics.Texture;
import com.deadland.model.menu.BuildingSpirit;
import com.deadland.model.menu.MenuButton;

/**
 * Created by inver on 10.08.2014.
 */
public class WallButton extends MenuButton {
    public static Texture texture = new Texture("menu_wall.png");

    public WallButton(float x, float y) {
        super(x, y);
    }

    @Override
    protected Texture getTexture() {
        return texture;
    }

    @Override
    protected BuildingSpirit createSpirit(float x, float y) {
        return new WallSpirit(x, y, this);
    }
}
