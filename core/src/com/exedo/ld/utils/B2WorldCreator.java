package com.exedo.ld.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.exedo.ld.LudumDare;
import com.exedo.ld.screens.GameScreen;

public class B2WorldCreator {

    public B2WorldCreator(GameScreen screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set((rect.getX() + rect.getWidth() / 2) / LudumDare.PPM, (rect.getY() + rect.getHeight() / 2) / LudumDare.PPM);

            body = world.createBody(bDef);

            shape.setAsBox((rect.getWidth() / 2) / LudumDare.PPM, (rect.getHeight() / 2) / LudumDare.PPM);
            fDef.shape = shape;
            body.createFixture(fDef);
        }
    }
}
