package com.deadland.model.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by inver on 09.08.2014.
 */
public class MainTower extends Building {
    public static Texture texture = new Texture("building_mainTower.png");

    public float buildingRadius = 250;

    public MainTower(float x, float y) {
        super(x, y, 10);

        boundingRectangle = new Rectangle(x, y, 32, 32);
    }

    @Override
    protected void initSprite(float x, float y) {
        sprite = new Sprite(texture);
        sprite.setSize(32, 32);
        sprite.setCenter(16, 16);
        sprite.setOrigin(16, 16);
        sprite.setPosition(x, y);
    }

    @Override
    public int getPrice() {
        return 500;
    }
}
