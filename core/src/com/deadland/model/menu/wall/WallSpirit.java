package com.deadland.model.menu.wall;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.deadland.Assets;
import com.deadland.model.building.Building;
import com.deadland.model.building.Wall;
import com.deadland.model.menu.BuildingSpirit;
import com.deadland.model.menu.MenuButton;

/**
 * Created by inver on 10.08.2014.
 */
public class WallSpirit extends BuildingSpirit {
    public WallSpirit(float x, float y, MenuButton e) {
        super(x, y, e);
        sprite.setSize(16, 16);
        boundingRectangle = new Rectangle(x, y, 16, 16);
    }

    @Override
    protected Texture getTexture() {
        return Assets.Textures.buildingWallGreenTransparent;
    }

    @Override
    protected void setCollidesTexture() {
        sprite.setTexture(Assets.Textures.buildingWallRedTransparent);
    }

    @Override
    protected void setNormalTexture() {
        sprite.setTexture(Assets.Textures.buildingWallGreenTransparent);
    }

    @Override
    protected Building createBuilding(float x, float y) {
        return new Wall(x - 16, y - 16, 300);
    }

    @Override
    public void update() {
        super.update();
        boundingRectangle.setPosition(sprite.getX(), sprite.getY());
    }
}
