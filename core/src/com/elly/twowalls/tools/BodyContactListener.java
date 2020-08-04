package com.elly.twowalls.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.elly.twowalls.Player;


public class BodyContactListener implements ContactListener {

    private void contactWithPlayer(Fixture player, Fixture object){
        ((Player)player.getUserData()).takeDamage();
    }
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        if (fixtureA.getUserData().getClass().equals(Player.class))
            contactWithPlayer(fixtureA, fixtureB);
        else if (fixtureB.getUserData().getClass().equals(Player.class))
            contactWithPlayer(fixtureB, fixtureA);
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
