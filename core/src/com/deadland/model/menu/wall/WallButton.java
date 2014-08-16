package com.deadland.model.menu.wall;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deadland.Assets;
import com.deadland.model.menu.BuildingSpirit;
import com.deadland.model.menu.MenuButton;

/**
 * Created by inver on 10.08.2014.
 */
public class WallButton extends MenuButton {
    public WallButton(float x, float y, String menuName) {
        super(x, y, menuName);
    }

    @Override
    protected TextureRegion getTexture() {
        return Assets.Textures.menuWall;
    }

    @Override
    protected BuildingSpirit createSpirit(float x, float y) {
        return new WallSpirit(x, y, this);
    }
}
