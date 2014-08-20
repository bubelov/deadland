package com.deadland.model.building;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.deadland.Assets;
import com.deadland.base.model.BaseEntity;

/**
 * @author Alexey Nevinsky
 * @version 18.08.14 23:06
 */
public class Stone extends BaseEntity {
    public Stone(World world, float length, float width) {
        super(world, length, width);

        BodyDef def = new BodyDef();
//        def.type = MathUtils.random(1) == 1 ? BodyDef.BodyType.DynamicBody : BodyDef.BodyType.StaticBody;
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        body.setBullet(false);

        PolygonShape ps = new PolygonShape();
        ps.setAsBox(length, width);
        //second parameter (density) is 0, bcz it is static object
        fixture = body.createFixture(ps, 0);
        ps.dispose();

        initSprite();
    }

    private void initSprite() {
        sprite = new Sprite(Assets.Textures.stone);
        sprite.setSize(length, width);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(body.getPosition().x, body.getPosition().y);
    }

    @Override
    public void setPosition(float x, float y) {
        body.setTransform(x, y, body.getAngle());
        sprite.setPosition(x, y);
    }

    protected void move() {
        body.setAngularVelocity(2);
        float vx = (float) (Math.cos(body.getAngle()) * 277);
        float vy = (float) (Math.sin(body.getAngle()) * 277);
        body.setLinearVelocity(vx, vy);
    }

    @Override
    public void update(float delta) {
        move();
    }
}
