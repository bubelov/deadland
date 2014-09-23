package com.deadland.base;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.deadland.base.model.Entity;
import com.deadland.base.physics.PhysicsUtils;

/**
 * Created by linv3r on 16.09.14.
 */
public class GameContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Entity e1 = PhysicsUtils.getEntityFromFixture(contact.getFixtureA());
        Entity e2 = PhysicsUtils.getEntityFromFixture(contact.getFixtureB());
        if (e1 == null || e2 == null) {

        } else {
            e1.beginContact(e2);
            e2.beginContact(e1);
        }
    }

    @Override
    public void endContact(Contact contact) {
        Entity e1 = PhysicsUtils.getEntityFromFixture(contact.getFixtureA());
        Entity e2 = PhysicsUtils.getEntityFromFixture(contact.getFixtureB());
        if (e1 == null || e2 == null) {

        } else {
            e1.beginContact(e2);
            e2.beginContact(e1);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
