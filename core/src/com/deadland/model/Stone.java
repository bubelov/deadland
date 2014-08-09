package com.deadland.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class Stone extends Entity {
    public static Texture texture = new Texture("stone.jpg");
    public static Texture texture2 = new Texture("stone2.jpg");

    public Stone(float x, float y) {
        sprite = new Sprite(x % 2 == 0 ? texture : texture2);
        sprite.setSize(32, 32);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x - x % 32, y - y % 32);

        z = -101;
    }
}