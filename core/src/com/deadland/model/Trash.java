package com.deadland.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.deadland.EntityManager;
import com.deadland.ResourcesManager;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class Trash extends Entity {
    public static Texture texture = new Texture("trash.png");

    public Trash(float x, float y) {
        sprite = new Sprite(texture);
        sprite.setSize(64, 64);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);

        boundingRectangle = new Rectangle(x(), y(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void onCollision(Entity entity) {
        if (entity instanceof Hero) {
            EntityManager.instance.destroy(this);
            ResourcesManager.instance.spendTrash(-2500);
        }
    }
}
