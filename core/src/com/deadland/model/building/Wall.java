package com.deadland.model.building;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.deadland.Assets;

/**
 * Created by inver on 09.08.2014.
 */
public class Wall extends Building {
    public Wall(float x, float y, float health) {
        super(x, y, health);

        boundingRectangle = new Rectangle(x, y, 16, 16);
    }

    @Override
    protected void initSprite(float x, float y) {
        sprite = new Sprite(Assets.Textures.buildingWall);
        sprite.setSize(16, 16);
        sprite.setCenter(8, 8);
        sprite.setOrigin(8, 8);
        sprite.setPosition(x, y);
    }

    @Override
    public int getPrice() {
        return 20;
    }
}
