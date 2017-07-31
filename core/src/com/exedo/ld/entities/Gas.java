package com.exedo.ld.entities;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.exedo.ld.LudumDare;
import com.exedo.ld.screens.GameScreen;
import com.exedo.ld.screens.SplashScreen;

public class Gas extends Sprite {
    public World world;
    public Body body;

    private GameScreen screen;

    public boolean collected;
    public boolean destroyed;

    public Gas(GameScreen screen, float x, float y) {
        this.world = screen.getWorld();
        this.screen = screen;
        this.collected = false;
        this.destroyed = false;

        setPosition(x, y);
        defineGas();
        setBounds(0, 0, 16 / LudumDare.PPM, 16 / LudumDare.PPM);
        setRegion(SplashScreen.manager.get("gas.png", Texture.class));
    }

    public void defineGas() {
        BodyDef bDef = new BodyDef();
        bDef.position.set(getX(), getY());
        bDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(8 / LudumDare.PPM, 8 / LudumDare.PPM);
        fDef.shape = shape;
        body.createFixture(fDef).setUserData(this);
    }

    public void update(float delta) {
        if(collected && !destroyed) {
            world.destroyBody(body);
            destroyed = true;
            SplashScreen.manager.get("gas.wav", Sound.class).play();
        } else if(!destroyed) {
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        }
    }

    public void collect() {collected = true; }

    public void draw(Batch batch) {
        if(!destroyed) {
            super.draw(batch);
        }
    }
}
