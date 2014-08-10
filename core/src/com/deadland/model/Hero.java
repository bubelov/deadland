package com.deadland.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.deadland.*;
import com.deadland.support.Health;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class Hero extends Entity {
    public static Texture texture = new Texture("truck.png");
    public static Texture gunTexture = new Texture("hero_gun.png");

    public Vector2 destination = new Vector2(256, 256);

    public Health health;

    public float shootTimeoutSeconds;

    private Sprite gunSprite;

    private float gunRotation = 0;

    private boolean moving;

    public Hero(float x, float y) {
        sprite = new Sprite(texture);
        sprite.setSize(147 / 1.5f, 72 / 1.5f);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);

        boundingCircle = new Circle(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2, 20);
        health = new Health(this, 100, 40, 60);

        gunSprite = new Sprite(gunTexture);
        gunSprite.setSize(78 / 1.5f, 26 / 1.5f);
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

                if (EntityUtils.collidesWithBuilding(this, EntityManager.instance.entities)) {
                    sprite.translate(-movementVector.x * 120 * Gdx.graphics.getDeltaTime(), -movementVector.y * 120 * Gdx.graphics.getDeltaTime());
                }

                boundingCircle.setPosition(sprite.getX() + sprite.getWidth() / 2 + (MathUtils.cos(MathUtils.degreesToRadians * sprite.getRotation()) * 30), sprite.getY() + sprite.getHeight() / 2 + (MathUtils.sin(MathUtils.degreesToRadians * sprite.getRotation()) * 30));
                gunSprite.setPosition(x() + sprite.getWidth() / 2 - gunSprite.getWidth() / 2 + (MathUtils.cos(MathUtils.degreesToRadians * sprite.getRotation()) * -30), y() + sprite.getHeight() / 2 - gunSprite.getHeight() / 2 + (MathUtils.sin(MathUtils.degreesToRadians * sprite.getRotation()) * -30));
                gunSprite.setRotation(sprite.getRotation() - 180 + gunRotation);
            }
        }

        shootTimeoutSeconds -= Gdx.graphics.getDeltaTime();

        if (shootTimeoutSeconds <= 0) {
            for (Entity entity : EntityManager.instance.entities) {
                if (entity instanceof Zombie && Vector2.dst(x(), y(), entity.centerX(), entity.centerY()) < 300) {
                    Vector2 directionVector = new Vector2(entity.centerX(), entity.centerY()).sub(centerX(), centerY());
                    float angle = directionVector.angle() - sprite.getRotation();

                    angle %= 360;
                    angle = Math.abs(angle);

                    //System.out.println("Angle: " + angle);

                    if (angle >= 180 - 45 && angle <= 180 + 45) {
                        if (ResourcesManager.instance.spendWeapon(1)) {
                            EntityManager.instance.add(new Bullet(gunSprite.getX() + gunSprite.getWidth() / 2 + (MathUtils.cos(MathUtils.degreesToRadians * gunSprite.getRotation()) * +20), gunSprite.getY() + gunSprite.getHeight() / 2 + (MathUtils.sin(MathUtils.degreesToRadians * gunSprite.getRotation()) * +20), entity.x(), entity.y()));
                            shootTimeoutSeconds = 0.1f;
                        }

                        gunRotation = directionVector.angle() - sprite.getRotation() + 180;
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
