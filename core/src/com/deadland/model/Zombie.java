package com.deadland.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class Zombie extends Entity {
    public static Texture texture = new Texture("zombie.jpeg");

    public Vector2 destination = null;

    public Zombie(float x, float y) {
        sprite = new Sprite(texture);
        sprite.setSize(28, 19);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);
    }

    @Override
    public void update() {
        super.update();

        if (destination != null){
            Vector2 movementVector = new Vector2(destination);
            movementVector.sub(sprite.getX(), sprite.getY());

            sprite.setRotation(movementVector.angle());
            sprite.translate(movementVector.nor().x * 3, movementVector.nor().y * 3);

        }
    }
}
