package com.deadland.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public abstract class Entity {
    public Sprite sprite;
    public Circle boundingCircle;
    public Rectangle boundingRectangle;
    public int z;

    public float x() {
        return sprite.getX();
    }

    public float y() {
        return sprite.getY();
    }

    public float centerX() {
        return x() + sprite.getWidth() / 2;
    }

    public float centerY() {
        return y() + sprite.getHeight() / 2;
    }

//    protected void initSprite(Texture texture, float sizeX, float sizeY) {
//
//        sprite = new Sprite(texture);
//        sprite.setSize(sizeX, sizeY);
//
//        sprite.setPosition(x, y);
//    }

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
