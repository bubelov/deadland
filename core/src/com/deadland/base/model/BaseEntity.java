package com.deadland.base.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Alexey Nevinsky
 * @version 18.08.14 23:03
 */
public class BaseEntity {
    protected Body body;
    protected Fixture fixture;

    protected float width;
    protected float length;

    //todo List of Sprite
    protected Sprite sprite;

    public BaseEntity(World world, float length, float width) {
        this.length = length;
        this.width = width;
    }

    public void setPosition(float x, float y) {

    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void update(float delta) {
        // Nothing to do here
    }

    public void onTap(float x, float y, int count, int button) {

    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
