package com.deadland.base.physics;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.deadland.base.model.Entity;

/**
 * Created by linv3r on 16.09.14.
 */
public class PhysicsUtils {
    public static Entity getEntityFromFixture(Fixture f) {
        Entity e = (Entity) f.getBody().getUserData();
        return e;
    }

}
