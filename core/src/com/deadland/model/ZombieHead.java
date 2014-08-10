package com.deadland.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class ZombieHead extends Entity {
    public static Texture texture = new Texture("zombie_head.png");

    public ZombieHead(float x, float y) {
        sprite = new Sprite(texture);
        sprite.setSize(13, 9);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);

        z = -50;
    }
}
