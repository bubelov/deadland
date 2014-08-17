package com.deadland.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.deadland.Assets;
import com.deadland.EntityManager;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class Gates extends Entity {
    boolean visible = true;

    public Gates(float x, float y) {
        sprite = new Sprite(Assets.Textures.gates);
        sprite.setSize(32, 192);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);

        z = 0;

        boundingRectangle = new Rectangle(x(), y(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void update() {
        super.update();

        visible = true;

        for (Entity entity : EntityManager.instance.entities) {
            if (entity instanceof Hero) {
                Hero hero = (Hero)entity;

                if (Vector2.dst(x(), y(), hero.x(), hero.y()) < 200) {
                    visible = false;
                }
            }
        }

        if (!visible) {
            boundingRectangle.setPosition(-1000, -1000);
        } else {
            boundingRectangle.setPosition(x(), y());
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        if (visible) {
            super.render(batch);
        }
    }
}
