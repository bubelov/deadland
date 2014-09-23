package com.deadland.model.zombie;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.deadland.Assets;
import com.deadland.base.model.Entity;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class ZombieHand extends Entity {
    public ZombieHand(float x, float y) {
        sprite = new Sprite(Assets.Textures.zombieHand);
        sprite.setSize(40, 14);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);

        z = -50;
        sprite.setRotation(MathUtils.random(360));
    }
}
