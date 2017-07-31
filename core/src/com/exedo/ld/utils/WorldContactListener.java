package com.exedo.ld.utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.exedo.ld.entities.Battery;
import com.exedo.ld.entities.Chip;
import com.exedo.ld.entities.Gas;
import com.exedo.ld.entities.SolarPanel;

public class WorldContactListener implements ContactListener{
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "player" || fixB.getUserData() == "player") {
            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture object = player == fixA ? fixB : fixA;

            if(object.getUserData() instanceof Battery) {
                ((Battery) object.getUserData()).collect();
            }

            if(object.getUserData() instanceof SolarPanel) {
                ((SolarPanel) object.getUserData()).collect();
            }

            if(object.getUserData() instanceof Gas) {
                ((Gas) object.getUserData()).collect();
            }

            if(object.getUserData() instanceof Chip) {
                ((Chip) object.getUserData()).collect();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
