package com.deadland.base.model;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.deadland.Constants;

/**
 * @author Alexey Nevinsky
 * @version 24.08.14 0:02
 */
public abstract class PhysicsEntity extends Entity {
    protected float density;

    protected Body body;
    protected Fixture fixture;

    protected PhysicsEntity(World world, float x, float y, float density) {
        this.density = density;
        initSprite();
        initPhysicsParams(world, x, y, 0, false);
        updatePosAndAngle();
    }

    protected void initPhysicsParams(World world, float xPos, float yPos, float angle, boolean isBullet) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(xPos * Constants.METER_PER_PIXEL, yPos * Constants.METER_PER_PIXEL);
        bodyDef.angle = angle;
        body = world.createBody(bodyDef);
        body.setBullet(isBullet);

        PolygonShape ps = new PolygonShape();
        ps.setAsBox(
                sprite.getWidth() * Constants.METER_PER_PIXEL / 2,
                sprite.getHeight() * Constants.METER_PER_PIXEL / 2
        );
        fixture = body.createFixture(ps, density);
        ps.dispose();
    }

    protected abstract void initSprite();

    @Override
    public void update(float delta) {
        super.update(delta);
        updatePosAndAngle();
    }

    protected void updatePosition() {
        float x = body.getPosition().x * Constants.PIXEL_PER_METER;
        float y = body.getPosition().y * Constants.PIXEL_PER_METER;
        sprite.setPosition(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2);
    }

    protected void updatePosAndAngle() {
        updatePosition();
        float angle = body.getAngle();
        sprite.setRotation(MathUtils.radiansToDegrees * angle);
    }
}
