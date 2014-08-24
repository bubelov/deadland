package com.deadland.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.deadland.Assets;
import com.deadland.base.model.Entity;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class BloodMess extends Entity {
    public BloodMess(float x, float y) {
        sprite = new Sprite(Assets.Textures.bloodmess);
        sprite.setSize(64, 64);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);

        z = -100;

        sprite.setRotation(MathUtils.random(360));
        sprite.setScale(MathUtils.random(0.7f, 1.3f));
    }
}
