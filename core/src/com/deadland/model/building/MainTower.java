package com.deadland.model.building;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.deadland.Assets;

/**
 * Created by inver on 09.08.2014.
 */
public class MainTower extends Building {
    private static final int WIDTH = 64;
    private static final int HEIGHT = 64;

    public float buildingRadius = 250;

    public MainTower(float x, float y) {
        super(x, y, 10);

        boundingRectangle = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    @Override
    protected void initSprite(float x, float y) {
        sprite = new Sprite(Assets.Textures.buildingMainTower);
        sprite.setSize(WIDTH, HEIGHT);
        sprite.setCenter(WIDTH / 2, HEIGHT / 2);
        sprite.setOrigin(WIDTH / 2, HEIGHT / 2);
        sprite.setPosition(x, y);
    }

    @Override
    public int getPrice() {
        return 500;
    }
}
