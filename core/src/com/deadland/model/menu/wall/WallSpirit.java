package com.deadland.model.menu.wall;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.deadland.model.building.Building;
import com.deadland.model.building.Wall;
import com.deadland.model.menu.BuildingSpirit;
import com.deadland.model.menu.MenuButton;

/**
 * Created by inver on 10.08.2014.
 */
public class WallSpirit extends BuildingSpirit {
    public static Texture greenTexture = new Texture("building_wall_green_transparent.png");
    public static Texture redTexture = new Texture("building_wall_red_transparent.png");

    public WallSpirit(float x, float y, MenuButton e) {
        super(x, y, e);
        sprite.setSize(16, 16);
        boundingRectangle = new Rectangle(x, y, 16, 16);
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
        return new Wall(x - 16, y - 16, 300);
    }

    @Override
    public void update() {
        super.update();
        boundingRectangle.setPosition(sprite.getX(), sprite.getY());
    }
}
