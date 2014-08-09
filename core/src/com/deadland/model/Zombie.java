package com.deadland.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.deadland.EntityManager;
import com.deadland.support.Health;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class Zombie extends Entity {
    public static Texture texture = new Texture("zombie.jpeg");

    public Vector2 destination = null;

//    public float health = 10;

    public float moveTimeoutSeconds = MathUtils.random(0, 40);

    private Health health;

    public Zombie(float x, float y) {
        sprite = new Sprite(texture);
        sprite.setSize(28, 19);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);

        boundingCircle = new Circle(x, y, 15);

        health = new Health(this, 30, 30, 30);
    }

    @Override
    public void render(SpriteBatch batch) {
        health.render(batch);
        super.render(batch);
    }

    @Override
    public void update() {
        super.update();

        boolean attacking = false;

        for (Entity entity : EntityManager.instance.entities) {
            if (entity instanceof Hero) {
                Hero hero = (Hero) entity;

                if (Vector2.dst(sprite.getX(), sprite.getY(), hero.sprite.getX(), hero.sprite.getY()) < 300) {
                    attacking = true;
                    destination = new Vector2(hero.sprite.getX(), hero.sprite.getY());
                }
            }
        }

        if (destination != null) {
            Vector2 movementVector = new Vector2(destination);
            movementVector.sub(sprite.getX(), sprite.getY());

            sprite.setRotation(movementVector.angle());
            sprite.translate(movementVector.nor().x * 3, movementVector.nor().y * 3);


            boundingCircle.setPosition(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
        }
        health.update(sprite.getX(), sprite.getY());

        if (!attacking && destination != null && destination.dst(sprite.getX(), sprite.getY()) < 5) {
            destination = null;
            moveTimeoutSeconds = MathUtils.random(0, 40);
        }

        moveTimeoutSeconds -= Gdx.graphics.getDeltaTime();

        if (destination == null && moveTimeoutSeconds <= 0) {
            destination = new Vector2(sprite.getX() + MathUtils.random(-100, 100), sprite.getY() + MathUtils.random(-100, 100));
        }
    }

    public void wound(float val) {
        health.wound(val);
    }

    public boolean isDead() {
        return health.isDead();
    }
}
