package com.exedo.ld.entities;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.exedo.ld.LudumDare;
import com.exedo.ld.screens.GameScreen;
import com.exedo.ld.screens.SplashScreen;

public class Player extends Sprite {
    public World world;
    public Body body;
    private Array<Sound> spawnSounds;

    public boolean hasPanel;
    public boolean hasGas;
    public boolean hasChip;

    public Player(GameScreen screen) {
        this.world = screen.getWorld();

        definePlayer();

        spawnSounds = new Array<Sound>();
        spawnSounds.add(SplashScreen.manager.get("spawn1.wav", Sound.class));
        spawnSounds.add(SplashScreen.manager.get("spawn2.wav", Sound.class));
        spawnSounds.add(SplashScreen.manager.get("spawn3.wav", Sound.class));
        spawnSounds.add(SplashScreen.manager.get("spawn4.wav", Sound.class));

        setBounds(0, 0, 32 / LudumDare.PPM, 32 / LudumDare.PPM);
        setRegion(SplashScreen.manager.get("player.png", Texture.class));

        int soundChooser = MathUtils.random(0, 3);
        spawnSounds.get(soundChooser).play();

        hasPanel = false;
        hasGas = false;
        hasChip = true;
    }

    public void update(float delta) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2 );
    }

    public void definePlayer() {
        BodyDef bDef = new BodyDef();
        bDef.position.set(312 / LudumDare.PPM, 172 / LudumDare.PPM);
        bDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(16 / LudumDare.PPM);

        fDef.shape = shape;
        body.createFixture(fDef).setUserData("player");
    }
}
