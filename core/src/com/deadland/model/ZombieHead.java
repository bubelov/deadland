package com.deadland.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.deadland.Assets;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class ZombieHead extends Entity {
    public ZombieHead(float x, float y) {
        sprite = new Sprite(Assets.Textures.zombieHead);
        sprite.setSize(26, 18);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);

        z = -50;
        sprite.setRotation(MathUtils.random(360));
    }
}
