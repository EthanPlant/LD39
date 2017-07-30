package com.exedo.ld.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.exedo.ld.LudumDare;
import com.exedo.ld.entities.Battery;
import com.exedo.ld.entities.Player;
import com.exedo.ld.ui.Hud;
import com.exedo.ld.utils.B2WorldCreator;
import com.exedo.ld.utils.WorldContactListener;

public class GameScreen implements Screen{
    private LudumDare game;

    private OrthographicCamera cam;
    private Viewport port;

    public Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;

    private Player player;

    private Array<Battery> batteries;

    private float spawnCount;
    private float timeCount;
    private float totalTime;

    private Music bgm;

    private Sound powerLow;
    private Sound powerCrit;
    private Sound noPower;

    public GameScreen(LudumDare game) {
        this.game = game;

        cam = new OrthographicCamera();
        port = new FitViewport(LudumDare.WIDTH / LudumDare.PPM, LudumDare.HEIGHT / LudumDare.PPM, cam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / LudumDare.PPM);
        cam.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);

        hud = new Hud(game.getBatch());

        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();
        creator = new B2WorldCreator(this);

        player = new Player(this);

        batteries = new Array<Battery>();

        world.setContactListener(new WorldContactListener());

        bgm = Gdx.audio.newMusic(Gdx.files.internal("bgm.ogg"));

        powerLow = Gdx.audio.newSound(Gdx.files.internal("powerlow.wav"));
        powerCrit = Gdx.audio.newSound(Gdx.files.internal("powercrit.wav"));
        noPower = Gdx.audio.newSound(Gdx.files.internal("loss.wav"));

        bgm.setLooping(true);
        bgm.play();
    }

    @Override
    public void show() {

    }

    public void handleInput(float delta) {
        player.body.setLinearVelocity(0, 0);
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.body.setLinearVelocity(0, 3);
            player.setRegion(player.textureUp);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.body.setLinearVelocity(0, -3);
            player.setRegion(player.textureDown);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.body.setLinearVelocity(-3, 0);
            player.setRegion(player.textureLeft);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.body.setLinearVelocity(3, 0);
            player.setRegion(player.textureRight);
        }
    }

    public void update(float delta) {
        timeCount += delta;

        handleInput(delta);

        spawn(delta);

        world.step(1/60f, 6, 2);

        hud.update(delta);

        player.update(delta);

        cam.update();
        renderer.setView(cam);

        for(int i = 0; i < batteries.size; i++) {
            Battery b = batteries.get(i);
            b.update(delta);
            if(b.collected) {
                hud.addScore();
                hud.setFuel(1);
                batteries.removeIndex(i);
            }
        }

        if(timeCount >= 1) {

            if (hud.getFuel() == 4) {
                powerLow.play();
            }

            if (hud.getFuel() == 2) {
                powerCrit.play();
            }

            if (hud.getFuel() == 0) {
                noPower.play();
                game.setScreen(new GameOver(game));
                bgm.pause();
            }

            timeCount = 0;
        }
    }

    public void spawn(float delta) {
        totalTime += delta;
        spawnCount += delta;
        if(totalTime <= 30) {
            if(spawnCount >= 1) {
                Battery b = new Battery(this, MathUtils.random(16 / LudumDare.PPM, (640 - 16) / LudumDare.PPM), MathUtils.random(16 / LudumDare.PPM, (360 - 16) / LudumDare.PPM));
                batteries.add(b);
                spawnCount = 0;
            }
        } else if(totalTime <= 60) {
            if(spawnCount >= 1.25) {
                Battery b = new Battery(this, MathUtils.random(16 / LudumDare.PPM, (640 - 16) / LudumDare.PPM), MathUtils.random(16 / LudumDare.PPM, (360 - 16) / LudumDare.PPM));
                batteries.add(b);
                spawnCount = 0;
            }
        } else if(totalTime <= 120) {
            if(spawnCount >= 1.5) {
                Battery b = new Battery(this, MathUtils.random(16 / LudumDare.PPM, (640 - 16) / LudumDare.PPM), MathUtils.random(16 / LudumDare.PPM, (360 - 16) / LudumDare.PPM));
                batteries.add(b);
                spawnCount = 0;
            }
        } else if(totalTime <= 240) {
            if(spawnCount >= 2) {
                Battery b = new Battery(this, MathUtils.random(16 / LudumDare.PPM, (640 - 16) / LudumDare.PPM), MathUtils.random(16 / LudumDare.PPM, (360 - 16) / LudumDare.PPM));
                batteries.add(b);
                spawnCount = 0;
            }
        } else if(totalTime <= 300) {
            if(spawnCount >= 2.5) {
                Battery b = new Battery(this, MathUtils.random(16 / LudumDare.PPM, (640 - 16) / LudumDare.PPM), MathUtils.random(16 / LudumDare.PPM, (360 - 16) / LudumDare.PPM));
                batteries.add(b);
                spawnCount = 0;
            }
        } else {
            if(spawnCount >= 3) {
                Battery b = new Battery(this, MathUtils.random(16 / LudumDare.PPM, (port.getWorldWidth() - 16) / LudumDare.PPM), MathUtils.random(16 / LudumDare.PPM, (port.getWorldHeight() - 16) / LudumDare.PPM));
                batteries.add(b);
                spawnCount = 0;
            }
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
//        b2dr.render(world, cam.combined);

        cam.update();
        game.getBatch().setProjectionMatrix(cam.combined);

        game.getBatch().begin();
        player.draw(game.getBatch());
            for (Battery b : batteries) {
                b.draw(game.getBatch());
            }
        game.getBatch().end();

        game.getBatch().setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
    }

    public TiledMap getMap() {return map;}

    public World getWorld() {return world; }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        bgm.dispose();
        hud.dispose();
    }
}
