package com.exedo.ld.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.exedo.ld.LudumDare;
import com.exedo.ld.entities.Player;
import com.exedo.ld.ui.Hud;
import com.exedo.ld.utils.B2WorldCreator;

public class GameScreen implements Screen{
    private LudumDare game;

    private OrthographicCamera cam;
    private Viewport port;

    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;

    private Player player;

    public GameScreen(LudumDare game) {
        this.game = game;

        cam = new OrthographicCamera();
        port = new FitViewport(LudumDare.WIDTH, LudumDare.HEIGHT, cam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        cam.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);

        hud = new Hud(game.getBatch());

        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();
        creator = new B2WorldCreator(this);

        player = new Player(this);
    }

    @Override
    public void show() {

    }

    public void handleInput(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.body.setLinearVelocity(0, 5);
            player.setRegion(player.textureUp);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.body.setLinearVelocity(0, -5);
            player.setRegion(player.textureDown);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.body.setLinearVelocity(-5, 0);
            player.setRegion(player.textureLeft);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.body.setLinearVelocity(5, 0);
            player.setRegion(player.textureRight);
        }
    }

    public void update(float delta) {
        handleInput(delta);


        world.step(1/60f, 6, 2);

        hud.update(delta);

        player.update(delta);

        cam.update();
        renderer.setView(cam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        b2dr.render(world, cam.combined);

        cam.update();
        game.getBatch().setProjectionMatrix(cam.combined);

        game.getBatch().begin();
        player.draw(game.getBatch());
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
        hud.dispose();
    }
}
