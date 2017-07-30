package com.exedo.ld.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.exedo.ld.LudumDare;
import com.exedo.ld.screens.GameScreen;

public class Player extends Sprite {
    public World world;
    public Body body;
    public Texture textureUp, textureDown, textureLeft, textureRight;
    private Array<Sound> spawnSounds;

    public Player(GameScreen screen) {
        this.world = screen.getWorld();

        definePlayer();

        textureUp = new Texture(Gdx.files.internal("player.png"));
        textureDown = new Texture(Gdx.files.internal("player-down.png"));
        textureLeft = new Texture(Gdx.files.internal("player-left.png"));
        textureRight = new Texture(Gdx.files.internal("player-right.png"));

        spawnSounds = new Array<Sound>();
        Sound spawn1 = Gdx.audio.newSound(Gdx.files.internal("spawn1.wav"));
        Sound spawn2 = Gdx.audio.newSound(Gdx.files.internal("spawn2.wav"));
        Sound spawn3 = Gdx.audio.newSound(Gdx.files.internal("spawn3.wav"));
        Sound spawn4 = Gdx.audio.newSound(Gdx.files.internal("spawn4.wav"));
        spawnSounds.add(spawn1);
        spawnSounds.add(spawn2);
        spawnSounds.add(spawn3);
        spawnSounds.add(spawn4);

        setBounds(0, 0, 32 / LudumDare.PPM, 32 / LudumDare.PPM);
        setRegion(textureUp);

        int soundChooser = MathUtils.random(0, 3);
        spawnSounds.get(soundChooser).play();
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
        shape.setRadius(8 / LudumDare.PPM);

        fDef.shape = shape;
        body.createFixture(fDef).setUserData("player");
    }
}
