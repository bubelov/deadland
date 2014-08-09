package com.deadland.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.deadland.EntityManager;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public abstract class Entity {
    public Sprite sprite;
    public Circle boundingCircle;
    public Rectangle boundingRectangle;

    public void update() {
        //Gdx.graphics.getDeltaTime()
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void onCollision(Entity entity) {

    }

    public void onTap(float x, float y, int count, int button) {

    }
}
