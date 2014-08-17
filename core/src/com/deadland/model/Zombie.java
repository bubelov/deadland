package com.deadland.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.deadland.Assets;
import com.deadland.EntityManager;
import com.deadland.EntityUtils;
import com.deadland.model.building.GunTower;
import com.deadland.support.Health;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class Zombie extends Entity {
    public static float POWER = 1f;

    public Vector2 destination = null;

    public float moveTimeoutSeconds = MathUtils.random(0, 40);

    private Health health;

    public Zombie(float x, float y) {
        sprite = new Sprite(Assets.Textures.zombie);
        sprite.setSize(28 *  2, 19 * 2);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);

        boundingCircle = new Circle(x, y, 10);

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
            if (entity instanceof GunTower) {
                if (Vector2.dst(sprite.getX(), sprite.getY(), entity.sprite.getX(), entity.sprite.getY()) < 600) {
                    attacking = true;
                    destination = new Vector2(entity.sprite.getX(), entity.sprite.getY());
                }
            }

            if (entity instanceof Hero) {
                Hero hero = (Hero) entity;

                if (Vector2.dst(sprite.getX(), sprite.getY(), hero.sprite.getX(), hero.sprite.getY()) < 600) {
                    attacking = true;
                    destination = new Vector2(hero.sprite.getX(), hero.sprite.getY());
                }
            }
        }

        if (destination != null) {
            Vector2 movementVector = new Vector2(destination);
            movementVector.sub(sprite.getX(), sprite.getY());

            sprite.setRotation(movementVector.angle());
            sprite.translate(movementVector.nor().x * 100 * Gdx.graphics.getDeltaTime(), movementVector.nor().y * 100 * Gdx.graphics.getDeltaTime());
            boundingCircle.setPosition(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);

            if (EntityUtils.collidesAny(this, EntityManager.instance.entities, true)) {
                sprite.setRotation(movementVector.angle());
                sprite.translate(-movementVector.nor().x * 100 * Gdx.graphics.getDeltaTime(), -movementVector.nor().y * 100 * Gdx.graphics.getDeltaTime());
                boundingCircle.setPosition(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
            }
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

    @Override
    public void onDestroy() {
        if (MathUtils.random(5) == 1) {
            EntityManager.instance.add(new ZombieHead(centerX(), centerY()));
        } else {
            if (MathUtils.random(5) == 1) {
                EntityManager.instance.add(new ZombieHand(centerX(), centerY()));
            }
        }
    }
}
