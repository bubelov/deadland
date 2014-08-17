package com.deadland.model.building;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.deadland.Assets;

/**
 * Created by inver on 09.08.2014.
 */
public class Wall extends Building {
    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;

    public Wall(float x, float y, float health) {
        super(x, y, health);

        boundingRectangle = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    @Override
    protected void initSprite(float x, float y) {
        sprite = new Sprite(Assets.Textures.buildingWall);
        sprite.setSize(WIDTH, HEIGHT);
        sprite.setCenter(WIDTH / 2, HEIGHT / 2);
        sprite.setOrigin(WIDTH / 2, HEIGHT / 2);
        sprite.setPosition(x, y);
    }

    @Override
    public int getPrice() {
        return 20;
    }
}
