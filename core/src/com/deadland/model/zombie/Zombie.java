package com.deadland.model.zombie;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.deadland.Assets;
import com.deadland.Constants;
import com.deadland.base.GameScene;
import com.deadland.base.model.Entity;
import com.deadland.base.physics.PhysicsFactory;
import com.deadland.model.BloodMess;
import com.deadland.model.vehicle.Car;
import com.deadland.support.Health;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class Zombie extends Entity {

    public static float POWER = 1f;
    public Vector2 destination = null;
    public float moveTimeoutSeconds = MathUtils.random(0, 40);
    private Sprite zombieSprite;
    private Body zombieBody;
    private State state = State.WALKING;
    private Health health;

    public Zombie(World world, GameScene scene, float x, float y) {
        super(world, scene);
        zombieSprite = new Sprite(Assets.Textures.zombie);
        zombieSprite.setSize(28, 19);
        zombieSprite.setCenter(zombieSprite.getWidth() / 2 - 8, zombieSprite.getHeight() / 2);
        zombieSprite.setOrigin(zombieSprite.getWidth() / 2 - 8, zombieSprite.getHeight() / 2);
        zombieSprite.setPosition(x - zombieSprite.getWidth() / 2, y - zombieSprite.getHeight() / 2);
        addSprite(zombieSprite);

        zombieBody = PhysicsFactory.createBody(world, BodyDef.BodyType.DynamicBody, new Vector2(x * Constants.METER_PER_PIXEL, y * Constants.METER_PER_PIXEL), 0);
        zombieBody.setUserData(this);
        float friction = 0.6f; //friction when rubbing against other shapes
        float restitution = 0.2f; //amount of force feedback when hitting something. >0 makes the car bounce off, it's fun!
        PhysicsFactory.createCircleFixture(zombieBody, 10 * Constants.METER_PER_PIXEL, 1.0f, friction, restitution, false);


//        boundingCircle = new Circle(x, y, 10);

//        health = new Health(this, 30, 30, 30);
    }

    @Override
    public void render(SpriteBatch batch) {
//        health.render(batch);
        super.render(batch);
    }

    @Override
    public void update(float delta) {
        super.update(delta);


        zombieSprite.setPosition(zombieBody.getPosition().x * Constants.PIXEL_PER_METER - zombieSprite.getOriginX(), zombieBody.getPosition().y * Constants.PIXEL_PER_METER - zombieSprite.getOriginY());
        if (state == State.WALKING) {
            // todo walking
        } else if (state == State.FLYING) {
            if (zombieBody.getLinearVelocity().len() < 100 * Constants.METER_PER_PIXEL) {
                die();
            }
        }
        zombieSprite.setRotation(MathUtils.atan2(zombieBody.getLinearVelocity().y, zombieBody.getLinearVelocity().x) * MathUtils.radiansToDegrees - 90);


//        boolean attacking = false;

//        for (Entity entity : EntityManager.instance.entities) {
//            if (entity instanceof GunTower) {
//                if (Vector2.dst(zombieSprite.getX(), zombieSprite.getY(), entity.zombieSprite.getX(), entity.zombieSprite.getY()) < 600) {
//                    attacking = true;
//                    destination = new Vector2(entity.zombieSprite.getX(), entity.zombieSprite.getY());
//                }
//            }
//
//            if (entity instanceof Hero) {
//                Hero hero = (Hero) entity;
//
//                if (Vector2.dst(zombieSprite.getX(), zombieSprite.getY(), hero.zombieSprite.getX(), hero.zombieSprite.getY()) < 600) {
//                    attacking = true;
//                    destination = new Vector2(hero.zombieSprite.getX(), hero.zombieSprite.getY());
//                }
//            }
//        }
//
//        if (destination != null) {
//            Vector2 movementVector = new Vector2(destination);
//            movementVector.sub(zombieSprite.getX(), zombieSprite.getY());
//
//            zombieSprite.setRotation(movementVector.angle());
//            zombieSprite.translate(movementVector.nor().x * 100 * Gdx.graphics.getDeltaTime(), movementVector.nor().y * 100 * Gdx.graphics.getDeltaTime());
//            boundingCircle.setPosition(zombieSprite.getX() + zombieSprite.getWidth() / 2, zombieSprite.getY() + zombieSprite.getHeight() / 2);
//
//            if (EntityUtils.collidesAny(this, EntityManager.instance.entities, true)) {
//                zombieSprite.setRotation(movementVector.angle());
//                zombieSprite.translate(-movementVector.nor().x * 100 * Gdx.graphics.getDeltaTime(), -movementVector.nor().y * 100 * Gdx.graphics.getDeltaTime());
//                boundingCircle.setPosition(zombieSprite.getX() + zombieSprite.getWidth() / 2, zombieSprite.getY() + zombieSprite.getHeight() / 2);
//            }
//        }
//        health.update(zombieSprite.getX(), zombieSprite.getY());
//
//        if (!attacking && destination != null && destination.dst(zombieSprite.getX(), zombieSprite.getY()) < 5) {
//            destination = null;
//            moveTimeoutSeconds = MathUtils.random(0, 40);
//        }
//
//        moveTimeoutSeconds -= Gdx.graphics.getDeltaTime();
//
//        if (destination == null && moveTimeoutSeconds <= 0) {
//            destination = new Vector2(zombieSprite.getX() + MathUtils.random(-100, 100), zombieSprite.getY() + MathUtils.random(-100, 100));
//        }
    }

    private void die() {
        state = State.DEAD;
        zombieBody.setLinearVelocity(0, 0);

        gameScene.create(new ZombiePart(zombieSprite.getX(), zombieSprite.getY(), true));
        gameScene.create(new BloodMess(zombieSprite.getX(), zombieSprite.getY()));
        world.destroyBody(zombieBody);
        gameScene.remove(this);
    }

    @Override
    public void beginContact(Entity e) {
        removeSprite(zombieSprite);
        zombieSprite = new Sprite(Assets.Textures.zombieFlying);
        zombieSprite.setSize(19, 61);
        zombieSprite.setCenter(zombieSprite.getWidth() / 2, zombieSprite.getHeight() / 2 + 14);
        zombieSprite.setOrigin(zombieSprite.getWidth() / 2, zombieSprite.getHeight() / 2 + 14);
        zombieSprite.setPosition(zombieBody.getPosition().x - zombieSprite.getWidth() / 2, zombieBody.getPosition().x - zombieSprite.getHeight() / 2 - 14);
        addSprite(zombieSprite);
        state = State.FLYING;

        if (e instanceof Car) {
            zombieBody.setLinearDamping(10);
        } else {
            zombieBody.setLinearDamping(20);
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
//        if (MathUtils.random(5) == 1) {
//            EntityManager.instance.add(new ZombieHead(centerX(), centerY()));
//        } else {
//            if (MathUtils.random(5) == 1) {
//                EntityManager.instance.add(new ZombieHand(centerX(), centerY()));
//            }
//        }
    }

    public enum State {
        WALKING,
        FLYING,
        DEAD;
    }
}
