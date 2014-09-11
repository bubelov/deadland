package com.deadland.base.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by linv3r on 11.09.14.
 */
public class PhysicsFactory {

    public static Body createBody(World world, BodyDef.BodyType bodyType, Vector2 position, float angle) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(position);
        bodyDef.angle = angle;
        return world.createBody(bodyDef);
    }

    public static void createBoxFixture(Body body, float width, float length, float density, float friction, float restitution, boolean isSensor) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.isSensor = isSensor;

        PolygonShape wheelShape = new PolygonShape();
        wheelShape.setAsBox(width, length);
        fixtureDef.shape = wheelShape;
        body.createFixture(fixtureDef);
        wheelShape.dispose();
    }

    public static void createBoxFixture(Body body, float width, float length, float density, boolean isSensor) {
        createBoxFixture(body, width, length, density, 0.2f, 0, isSensor);
    }
}
