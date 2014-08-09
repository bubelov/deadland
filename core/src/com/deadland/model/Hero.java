package com.deadland.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.deadland.EntityManager;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class Hero extends Entity {
    public static Texture texture = new Texture("hero.png");

    public Vector2 destination = new Vector2(256, 256);

    public float health = 100;

    public Hero(float x, float y) {
        sprite = new Sprite(texture);
        sprite.setSize(177 / 3, 96 / 3);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);

        boundingCircle = new Circle(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2, 20);
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
                //boundingRectangle.setPosition(sprite.getX(), sprite.getY());
                boundingCircle.setPosition(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
            }
        }
    }

    @Override
    public void onTap(float x, float y, int count, int button) {
        destination.set(x, y);
    }

    @Override
    public void onCollision(Entity entity) {
        if (entity instanceof Zombie) {
            health -= 1;
            EntityManager.instance.destroy(entity);
            EntityManager.instance.add(new BloodMess(entity.x(), entity.y()));

            System.out.println("HEALTH: " + health);
        }
    }
}
