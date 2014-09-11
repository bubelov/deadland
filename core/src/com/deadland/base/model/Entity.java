package com.deadland.base.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public abstract class Entity {
    @Deprecated
    public Sprite sprite;
    public Circle boundingCircle;
    public Rectangle boundingRectangle;
    public int z;
    protected List<Sprite> sprites;

    protected Entity() {
        sprites = new ArrayList<Sprite>();
    }

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

    public void update(float delta) {
        // Nothing to do here
    }

    public void render(SpriteBatch batch) {
        if (CollectionUtils.isNotEmpty(sprites)) {
            for (Sprite s : sprites) {
                s.draw(batch);
            }
        }
    }

    public void smartRender(SpriteBatch batch, float left, float top, float right, float bottom) {
        render(batch);
    }

    public void onCollision(Entity entity) {

    }

    public void onTap(float x, float y, int count, int button) {

    }

    public void keyDown(int button) {

    }

    public void keyUp(int button) {

    }

    public void onDestroy() {

    }

    protected void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }
}
