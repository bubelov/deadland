package com.deadland.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class Hero extends Entity {
    public static Texture texture = new Texture("hero.png");

    public Vector2 destination = new Vector2(256, 256);

    public Hero(float x, float y) {
        sprite = new Sprite(texture);
        sprite.setSize(177 / 3, 96 / 3);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);
    }

    @Override
    public void update() {
        super.update();

        if (sprite.getX() != destination.x || sprite.getY() != destination.y) {
            Vector2 movementVector = new Vector2(destination);
            movementVector.sub(sprite.getX(), sprite.getY());

            if (movementVector.len() < 5) {
                destination.set(sprite.getX(), sprite.getY());
            } else {
                sprite.setRotation(movementVector.angle());
                sprite.translate(movementVector.nor().x * 5, movementVector.nor().y * 5);
            }
        }
    }

    @Override
    public void onTap(float x, float y, int count, int button) {
        destination.set(x, y);
    }
}
