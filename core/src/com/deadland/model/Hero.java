package com.deadland.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.deadland.Assets;
import com.deadland.base.model.Entity;
import com.deadland.base.model.PhysicsEntity;
import com.deadland.support.Health;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class Hero extends PhysicsEntity {
    public Vector2 destination = new Vector2(512, 512);

    public Health health;

//    public float shootTimeoutSeconds;
//
//    private Sprite gunSprite;

//    private float gunRotation = 0;

//    private boolean moving;
//
//    private float painDurationSeconds;

    public Hero(World world, float x, float y) {
        super(world, x, y, 200);
    }

    protected void initSprite() {
        sprite = new Sprite(Assets.Textures.truck);
        sprite.setSize(147, 72);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        addSprite(sprite);

//        gunSprite = new Sprite(Assets.Textures.heroGun);
//        gunSprite.setSize(MeasureUtils.toPixels(2f * Constants.REAL_METER), MeasureUtils.toPixels(0.2f * Constants.REAL_METER));
//        gunSprite.setCenter(gunSprite.getWidth() / 2, gunSprite.getHeight() / 2);
//        gunSprite.setOrigin(gunSprite.getWidth() / 2, gunSprite.getHeight() / 2);
//        addSprite(sprite);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (destination == null) {
            return;
        }


//
//        if (sprite.getX() != destination.x || sprite.getY() != destination.y) {
//            Vector2 movementVector = new Vector2(destination);
//            movementVector.sub(sprite.getX(), sprite.getY());
//
//            if (movementVector.len() < 5) {
//                destination.set(sprite.getX(), sprite.getY());
//                moving = false;
//            } else {
//                moving = true;
//                sprite.setRotation(movementVector.angle());
//                sprite.translate(movementVector.nor().x * 240 * Gdx.graphics.getDeltaTime(), movementVector.nor().y * 240 * Gdx.graphics.getDeltaTime());
//
//                if (EntityUtils.collidesWithBuilding(this, EntityManager.instance.entities)) {
//                    sprite.translate(-movementVector.x * 240 * Gdx.graphics.getDeltaTime(), -movementVector.y * 240 * Gdx.graphics.getDeltaTime());
//                }
//
////                boundingCircle.setPosition(sprite.getX() + sprite.getWidth() / 2 + (MathUtils.cos(MathUtils.degreesToRadians * sprite.getRotation()) * 30), sprite.getY() + sprite.getHeight() / 2 + (MathUtils.sin(MathUtils.degreesToRadians * sprite.getRotation()) * 30));
////                gunSprite.setPosition(x() + sprite.getWidth() / 2 - gunSprite.getWidth() / 2 + (MathUtils.cos(MathUtils.degreesToRadians * sprite.getRotation()) * -30), y() + sprite.getHeight() / 2 - gunSprite.getHeight() / 2 + (MathUtils.sin(MathUtils.degreesToRadians * sprite.getRotation()) * -30));
////                gunSprite.setRotation(sprite.getRotation() - 180 + gunRotation);
//            }
//        }
//
//        shootTimeoutSeconds -= delta;
//
//        if (shootTimeoutSeconds <= 0) {
//            for (Entity entity : EntityManager.instance.entities) {
//                if ((entity instanceof Zombie || entity instanceof Cave) && Vector2.dst(x(), y(), entity.centerX(), entity.centerY()) < 600) {
//                    Vector2 directionVector = new Vector2(entity.centerX(), entity.centerY()).sub(centerX(), centerY());
//                    float angle = directionVector.angle() - sprite.getRotation();
//
//                    angle %= 360;
//                    angle = Math.abs(angle);
//
//                    //System.out.println("Angle: " + angle);
//
//                    if (angle >= 180 - 45 && angle <= 180 + 45) {
//                        if (ResourcesManager.instance.spendWeapon(1)) {
//                            EntityManager.instance.add(new Bullet(gunSprite.getX() + gunSprite.getWidth() / 2 + (MathUtils.cos(MathUtils.degreesToRadians * gunSprite.getRotation()) * +20), gunSprite.getY() + gunSprite.getHeight() / 2 + (MathUtils.sin(MathUtils.degreesToRadians * gunSprite.getRotation()) * +20), entity.centerX(), entity.centerY()));
//                            shootTimeoutSeconds = 0.1f;
//                        }
//
//                        gunRotation = directionVector.angle() - sprite.getRotation() + 180;
//                        break;
//                    }
//                }
//            }
//        }

//        health.update(sprite.getX(), sprite.getY());
    }

    @Override
    public void onTap(float x, float y, int count, int button) {
//        if (ControlManager.instance.isUnderConstruction == null) {
//            if (ControlManager.instance.justLeftConstruction) {
//                ControlManager.instance.justLeftConstruction = false;
//            } else {
//                destination.set(x, y);
//            }
//
//        }
    }

    @Override
    public void onCollision(Entity entity) {
//        if (entity instanceof Zombie) {
//            health.wound(1);
//
//            if (moving) {
//                EntityManager.instance.destroy(entity);
//                EntityManager.instance.add(new BloodMess(entity.x(), entity.y()));
//            } else {
//                painDurationSeconds = 0.5f;
//            }
//        }
//
//        if (health.isDead()) {
//            DeadlandGame.instance.gameOver = true;
//        }
    }

//    @Override
//    public void render(SpriteBatch batch) {
//        health.render(batch);
//        super.render(batch);
//        gunSprite.draw(batch);
//
//        painDurationSeconds -= Gdx.graphics.getDeltaTime();
//
//        if (painDurationSeconds > 0) {
//            batch.draw(Assets.Textures.pain, 0, 0, 8000, 6000);
//        }
//    }
}
