package com.deadland.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.deadland.Assets;
import com.deadland.base.model.BaseEntity;

/**
 * @author Alexey Nevinsky
 * @version 18.08.14 23:26
 */
public class Car extends BaseEntity {
    private Vector2 destination = new Vector2(512, 512);

    private float enginePower = 500;
    private float angularPower = 1f;

    public Car(World world, float x0, float y0) {
        super(world, 147 / 1.5f * 2f, 72 / 1.5f * 2f);

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        body.setBullet(false);

        PolygonShape ps = new PolygonShape();
        ps.setAsBox(length, width);
        fixture = body.createFixture(ps, 1000);
        ps.dispose();

        body.setTransform(x0, y0, 0);
        initSprite();
    }

    private void initSprite() {
        sprite = new Sprite(Assets.Textures.truck);
        sprite.setSize(length, width);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(body.getPosition().x, body.getPosition().y);
    }

    protected void move() {
        if (!body.getPosition().epsilonEquals(destination, 5)) {
            Vector2 movementVector = new Vector2(destination);
            movementVector.sub(body.getPosition());

            body.setAngularVelocity(angularPower);
            float vx = (float) (Math.cos(body.getAngle()) * enginePower);
            float vy = (float) (Math.sin(body.getAngle()) * enginePower);
            System.out.println(vx + " : " + vy);
            body.setLinearVelocity(vx, vy);
            sprite.setPosition(body.getPosition().x, body.getPosition().y);
            sprite.setRotation(MathUtils.radiansToDegrees * body.getAngle());
        } else {
            body.setLinearVelocity(0, 0);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        move();
    }

    @Override
    public void onTap(float x, float y, int count, int button) {
        destination.set(x, y);
    }

}
