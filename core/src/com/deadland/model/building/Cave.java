package com.deadland.model.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by inver on 10.08.2014.
 */
public class Cave extends Building {
    public static Texture texture = new Texture("building_cave.png");

    public Cave(float x, float y) {
        super(x, y, 500);
    }

    @Override
    protected void initSprite(float x, float y) {
        sprite = new Sprite(texture);
        sprite.setSize(64, 64);
        sprite.setCenter(32, 32);
        sprite.setOrigin(32, 32);
        sprite.setPosition(x, y);
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
