package com.deadland.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.deadland.Assets;
import com.deadland.EntityManager;
import com.deadland.ResourcesManager;
import com.deadland.base.model.Entity;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class Weapons extends Entity {
    public Weapons(float x, float y) {
        sprite = new Sprite(Assets.Textures.weapons);
        sprite.setSize(128, 128);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);

        boundingRectangle = new Rectangle(x(), y(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void onCollision(Entity entity) {
        if (entity instanceof Hero) {
            EntityManager.instance.destroy(this);
            ResourcesManager.instance.spendWeapon(-2500);
        }
    }
}
