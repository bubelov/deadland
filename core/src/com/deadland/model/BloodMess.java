package com.deadland.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class BloodMess extends Entity {
    public static Texture texture = new Texture("bloodmess.png");

    public BloodMess(float x, float y) {
        sprite = new Sprite(texture);
        sprite.setSize(32, 32);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);

        z = -100;
    }
}
