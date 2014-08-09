package com.deadland.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.deadland.ControlManager;
import com.deadland.DeadlandGame;
import com.deadland.EntityManager;
import com.deadland.ResourcesManager;
import com.deadland.support.Health;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class Hero extends Entity {
    public static Texture texture = new Texture("hero.png");
    public static Texture gunTexture = new Texture("hero_gun.png");

    public Vector2 destination = new Vector2(256, 256);

    public Health health;

    public float shootTimeoutSeconds;

    private Sprite gunSprite;

    private float gunRotation = 0;

    private boolean moving;

    public Hero(float x, float y) {
        sprite = new Sprite(texture);
        sprite.setSize(177 / 3, 96 / 3);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);

        boundingCircle = new Circle(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2, 20);
        health = new Health(this, 100, 40, 60);

        gunSprite = new Sprite(gunTexture);
        gunSprite.setSize(78 / 2, 26 / 2);
        gunSprite.setCenter(gunSprite.getWidth() / 2, gunSprite.getHeight() / 2);
        gunSprite.setOrigin(gunSprite.getWidth() / 2, gunSprite.getHeight() / 2);

        //gunSprite.setPosition(x() + sprite.getWidth() / 2 - gunSprite.getWidth() / 2, y() + sprite.getHeight() / 2 - gunSprite.getHeight() / 2);
    }

    @Override
    public void update() {
        super.update();

        if (destination == null) {
            return;
        }

        if (sprite.getX() != destination.x || sprite.getY() != destination.y) {
            Vector2 movementVector = new Vector2(destination);
            movementVector.sub(sprite.getX(), sprite.getY());

            if (movementVector.len() < 5) {
                destination.set(sprite.getX(), sprite.getY());
                moving = false;
            } else {
                moving = true;
                sprite.setRotation(movementVector.angle());
                sprite.translate(movementVector.nor().x * 120 * Gdx.graphics.getDeltaTime(), movementVector.nor().y * 120 * Gdx.graphics.getDeltaTime());
                //boundingRectangle.setPosition(sprite.getX(), sprite.getY());
                boundingCircle.setPosition(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
                gunSprite.setPosition(x() + sprite.getWidth() / 2 - gunSprite.getWidth() / 2, y() + sprite.getHeight() / 2 - gunSprite.getHeight() / 2);
                gunSprite.setRotation(sprite.getRotation() - 180 + gunRotation);
            }
        }

        shootTimeoutSeconds -= Gdx.graphics.getDeltaTime();

        if (shootTimeoutSeconds <= 0) {
            for (Entity entity : EntityManager.instance.entities) {
                if (entity instanceof Zombie && Vector2.dst(x(), y(), entity.x(), entity.y()) < 300) {
                    float angle = new Vector2(x(), y()).sub(entity.x(), entity.y()).angle() - sprite.getRotation();

                    if (angle > -45 && angle < 45) {
                        if (ResourcesManager.instance.spendWeapon(1)) {
                            EntityManager.instance.add(new Bullet(centerX(), centerY(), entity.x(), entity.y()));
                            shootTimeoutSeconds = 0.1f;
                        }
                        gunRotation = angle;
                    }
                    break;
                }
            }
        }
        health.update(sprite.getX(), sprite.getY());
    }

    @Override
    public void onTap(float x, float y, int count, int button) {
        if (ControlManager.instance.isUnderConstruction == null) {
            if (ControlManager.instance.justLeftConstruction) {
                ControlManager.instance.justLeftConstruction = false;
            } else {
                destination.set(x, y);
            }

        }
    }

    @Override
    public void onCollision(Entity entity) {
        if (entity instanceof Zombie) {
            health.wound(1);

            if (moving) {
                EntityManager.instance.destroy(entity);
                EntityManager.instance.add(new BloodMess(entity.x(), entity.y()));
            }
        }

        if (health.isDead()) {
            DeadlandGame.instance.gameOver = true;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        health.render(batch);
        super.render(batch);
        gunSprite.draw(batch);
    }
}
