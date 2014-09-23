package com.deadland.model.vehicle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.deadland.Assets;
import com.deadland.Constants;
import com.deadland.base.model.Entity;
import com.deadland.base.physics.PhysicsFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by linv3r on 11.09.14.
 */
public class Car extends Entity implements MovableObject {

    private Sprite carBodySprite;

    private Body body;
    private float width;
    private float length;
    private float angle;
    private float maxSteerAngle;
    private float maxSpeed;
    private float power;
    private float wheelAngle;
    private Steer steer;
    private ACCELERATE accelerate;

    private Vector2 position;
    private List<Wheel> wheels;

    public Car(World world, float posX, float posY) {
        this(world, 72 * Constants.METER_PER_PIXEL, 147 * Constants.METER_PER_PIXEL, new Vector2(posX, posY), 0, 1, 35, 0.4f);
    }

    public Car(World world, float width, float length, Vector2 position,
               float angle, float power, float maxSteerAngle, float maxSpeed) {

        steer = Steer.NONE;
        accelerate = ACCELERATE.NONE;

        this.width = width;
        this.length = length;
        this.angle = angle;
        this.maxSteerAngle = maxSteerAngle;
        this.maxSpeed = maxSpeed;
        this.power = power;
        this.position = position;
        this.wheelAngle = 0;

        body = PhysicsFactory.createBody(world, BodyDef.BodyType.DynamicBody, position, angle);
        body.setUserData(this);
        float friction = 0.6f; //friction when rubbing against other shapes
        float restitution = 0.4f; //amount of force feedback when hitting something. >0 makes the car bounce off, it's fun!
        PhysicsFactory.createBoxFixture(body, width / 2, length / 2, 1.0f, friction, restitution, false);

        //initialize wheels
        this.wheels = Arrays.asList(
                new Wheel(world, this, -30 * Constants.METER_PER_PIXEL, -50 * Constants.METER_PER_PIXEL, 20 * Constants.METER_PER_PIXEL, 40 * Constants.METER_PER_PIXEL, false, false), //top left
                new Wheel(world, this, 30 * Constants.METER_PER_PIXEL, -50 * Constants.METER_PER_PIXEL, 20 * Constants.METER_PER_PIXEL, 40 * Constants.METER_PER_PIXEL, false, false),  //top right
                new Wheel(world, this, -30 * Constants.METER_PER_PIXEL, 50 * Constants.METER_PER_PIXEL, 20 * Constants.METER_PER_PIXEL, 40 * Constants.METER_PER_PIXEL, true, true),//back left
                new Wheel(world, this, 30 * Constants.METER_PER_PIXEL, 50 * Constants.METER_PER_PIXEL, 20 * Constants.METER_PER_PIXEL, 40 * Constants.METER_PER_PIXEL, true, true)  //back right
        );

        initSprite(width * Constants.PIXEL_PER_METER, length * Constants.PIXEL_PER_METER);
    }

    private void initSprite(float width, float length) {
        carBodySprite = new Sprite(Assets.Textures.truck);
        carBodySprite.setSize(length, width);
        carBodySprite.setOrigin(carBodySprite.getWidth() / 2, carBodySprite.getHeight() / 2);
        carBodySprite.setCenter(carBodySprite.getWidth() / 2, carBodySprite.getHeight() / 2);
        addSprite(carBodySprite);
    }

    public Body getBody() {
        return body;
    }

    public List<Wheel> getPoweredWheels() {
        List<Wheel> poweredWheels = new ArrayList<Wheel>();
        for (Wheel wheel : this.wheels) {
            if (wheel.powered)
                poweredWheels.add(wheel);
        }
        return poweredWheels;
    }

    public Vector2 getLocalVelocity() {
        /*
        returns car's velocity vector relative to the car
	    */
        return this.body.getLocalVector(this.body.getLinearVelocityFromLocalPoint(new Vector2(0, 0)));
    }


    public List<Wheel> getRevolvingWheels() {
        List<Wheel> revolvingWheels = new ArrayList<Wheel>();
        for (Wheel wheel : this.wheels) {
            if (wheel.revolving)
                revolvingWheels.add(wheel);
        }
        return revolvingWheels;
    }

    public float getSpeedKMH() {
        Vector2 velocity = this.body.getLinearVelocity();
        float len = velocity.len();
        return (len / 1000) * 3600;
    }

    public void setSpeed(float speed) {
        /*
        speed - speed in kilometers per hour
	    */
        Vector2 velocity = this.body.getLinearVelocity();
        velocity = velocity.nor();
        velocity = new Vector2(velocity.x * ((speed * 1000.0f) / 3600.0f),
                velocity.y * ((speed * 1000.0f) / 3600.0f));
        this.body.setLinearVelocity(velocity);
    }

    public void update(float deltaTime) {

        //1. KILL SIDEWAYS VELOCITY

        for (Wheel wheel : wheels) {
            wheel.killSidewaysVelocity();
        }

        //2. SET WHEEL ANGLE

        //calculate the change in wheel's angle for this update
        float incr = (this.maxSteerAngle) * deltaTime * 5;

        if (this.steer == Steer.LEFT) {
            this.wheelAngle = Math.min(Math.max(this.wheelAngle, 0) + incr, this.maxSteerAngle); //increment angle without going over max steer
        } else if (this.steer == Steer.RIGHT) {
            this.wheelAngle = Math.max(Math.min(this.wheelAngle, 0) - incr, -this.maxSteerAngle); //decrement angle without going over max steer
        } else {
            this.wheelAngle = 0;
        }

        //update revolving wheels
        for (Wheel wheel : this.getRevolvingWheels()) {
            wheel.setAngle(this.wheelAngle);
        }

        //3. APPLY FORCE TO WHEELS
        Vector2 baseVector; //vector pointing in the direction force will be applied to a wheel ; relative to the wheel.

        //if accelerator is pressed down and speed limit has not been reached, go forwards
        if ((this.accelerate == ACCELERATE.BACKWARD) && (this.getSpeedKMH() < this.maxSpeed)) {
            baseVector = new Vector2(0, -1);
        } else if (this.accelerate == ACCELERATE.FORWARD) {
            //braking, but still moving forwards - increased force
            if (this.getLocalVelocity().y < 0)
                baseVector = new Vector2(0f, 1.3f);
                //going in reverse - less force
            else
                baseVector = new Vector2(0f, 0.7f);
        } else if (this.accelerate == ACCELERATE.NONE) {
            //slow down if not accelerating
            baseVector = new Vector2(0, 0);
            if (this.getSpeedKMH() < 7)
                this.setSpeed(0);
            else if (this.getLocalVelocity().y < 0)
                baseVector = new Vector2(0, 0.7f);
            else if (this.getLocalVelocity().y > 0)
                baseVector = new Vector2(0, -0.7f);
        } else
            baseVector = new Vector2(0, 0);

        //multiply by engine power, which gives us a force vector relative to the wheel
        Vector2 forceVector = new Vector2(this.power * baseVector.x, this.power * baseVector.y);

        //apply force to each wheel
        for (Wheel wheel : this.getPoweredWheels()) {
            Vector2 position = wheel.body.getWorldCenter();
            wheel.body.applyForce(wheel.body.getWorldVector(new Vector2(forceVector.x, forceVector.y)), position, true);
        }

        carBodySprite.setPosition(body.getPosition().x * Constants.PIXEL_PER_METER - carBodySprite.getWidth() / 2, body.getPosition().y * Constants.PIXEL_PER_METER - carBodySprite.getHeight() / 2);
        carBodySprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees + 90);

        System.out.println("Car Speed: " + this.getSpeedKMH());
        //if going very slow, stop - to prevent endless sliding

    }

    @Override
    public void goForward() {
        accelerate = ACCELERATE.FORWARD;
    }

    @Override
    public void goBackward() {
        accelerate = ACCELERATE.BACKWARD;
    }

    @Override
    public void steerRight() {
        steer = Steer.RIGHT;
    }

    @Override
    public void steerLeft() {
        steer = Steer.LEFT;
    }

    @Override
    public void stop() {
        accelerate = ACCELERATE.NONE;
    }

    @Override
    public void steerNone() {
        steer = Steer.NONE;
    }

    public enum Steer {
        LEFT,
        RIGHT,
        NONE
    }

    public enum ACCELERATE {
        FORWARD,
        BACKWARD,
        NONE
    }
}
