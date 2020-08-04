package com.elly.twowalls.tools;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class ColliderCreator {

    private static FixtureDef fdef = new FixtureDef();
    private static BodyDef bDef = new BodyDef();




    public static Body createBody(World world, Vector2 position, float gravityScale){
        bDef.position.x = position.x;
        bDef.position.y = position.y;
        bDef.gravityScale = gravityScale;
        bDef.type = BodyDef.BodyType.DynamicBody;
        return world.createBody(bDef);
    }

    public static Fixture createCollider(Body body, Shape shape){
        fdef.shape = shape;
        fdef.isSensor = true;
        fdef.friction = 0;
        fdef.restitution = 0;
        Fixture fixture = body.createFixture(fdef);
        return fixture;
    }

    public static Fixture createPolygonCollider(Body body, Vector2[] vertices){
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        fdef.shape = shape;
        fdef.isSensor = false;
        fdef.friction = 0;
        fdef.restitution = 0;
        Fixture fixture = body.createFixture(fdef);
        shape.dispose();
        return fixture;
    }

    public static Fixture createBoxCollider(Body body, float x, float y, float size) {
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(size, size, new Vector2(x, y), 0);
        fdef.shape = boxShape;
        fdef.isSensor = false;
        fdef.friction = 0;
        fdef.restitution = 0;
        Fixture fixture = body.createFixture(fdef);
        boxShape.dispose();
        return fixture;
    }
}
