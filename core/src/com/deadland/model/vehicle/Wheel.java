package com.deadland.model.vehicle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.deadland.base.physics.PhysicsFactory;

/**
 * Created by linv3r on 11.09.14.
 */
public class Wheel {

    public Car car;//car this wheel belongs to
    public boolean revolving; // does this wheel revolve when steering?
    public boolean powered; // is this wheel powered?
    public Body body;
    private float width; // width in meters
    private float length; // length in meters

    public Wheel(World world, Car car, float posX, float posY, float width, float length,
                 boolean revolving, boolean powered) {
        this.car = car;
        this.width = width;
        this.length = length;
        this.revolving = revolving;
        this.powered = powered;

        //init body
        body = PhysicsFactory.createBody(
                world, BodyDef.BodyType.DynamicBody, car.getBody().getWorldPoint(new Vector2(posX, posY)), car.getBody().getAngle()
        );
        //wheel does not participate in collision calculations: resulting complications are unnecessary. so isSensor = true
        PhysicsFactory.createBoxFixture(body, width / 2, length / 2, 1.0f, true);

        //create joint to connect wheel to body
        if (revolving) {
            RevoluteJointDef jointdef = new RevoluteJointDef();
            jointdef.initialize(car.getBody(), body, body.getWorldCenter());
            jointdef.enableMotor = false; //we'll be controlling the wheel's angle manually
            world.createJoint(jointdef);
        } else {
            PrismaticJointDef jointdef = new PrismaticJointDef();
            jointdef.initialize(car.getBody(), body, body.getWorldCenter(), new Vector2(1, 0));
            jointdef.enableLimit = true;
            jointdef.lowerTranslation = jointdef.upperTranslation = 0;
            world.createJoint(jointdef);
        }
    }

    /**
     * @param angle - wheel angle relative to car, in degrees
     */
    public void setAngle(float angle) {
        this.body.setTransform(body.getPosition(), car.getBody().getAngle() + (float) Math.toRadians(angle));
    }

    /**
     * @return get velocity vector relative to car
     */
    public Vector2 getLocalVelocity() {
        return car.getBody().getLocalVector(car.getBody().getLinearVelocityFromLocalPoint(this.body.getPosition()));
    }

    /**
     * @return a world unit vector pointing in the direction this wheel is moving
     */
    public Vector2 getDirectionVector() {
        Vector2 directionVector;
        if (this.getLocalVelocity().y > 0)
            directionVector = new Vector2(0, 1);
        else
            directionVector = new Vector2(0, -1);
        return directionVector.rotate((float) Math.toDegrees(this.body.getAngle()));
    }

    /**
     * substracts sideways velocity from this wheel's velocity vector and returns the remaining front-facing velocity vector
     *
     * @return
     */
    public Vector2 getKillVelocityVector() {
        Vector2 velocity = this.body.getLinearVelocity();
        Vector2 sidewaysAxis = this.getDirectionVector();
        float dotprod = velocity.dot(sidewaysAxis);
        return new Vector2(sidewaysAxis.x * dotprod, sidewaysAxis.y * dotprod);
    }

    /**
     * removes all sideways velocity from this wheels velocity
     */
    public void killSidewaysVelocity() {
        this.body.setLinearVelocity(this.getKillVelocityVector());
    }
}
