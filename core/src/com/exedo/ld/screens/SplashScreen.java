package com.exedo.ld.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.exedo.ld.LudumDare;

public class SplashScreen implements Screen {
    private LudumDare game;
    private OrthographicCamera cam;

    public static AssetManager manager = new AssetManager();

    public SplashScreen(LudumDare game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1920, 1080);

        manager.load("splash.png", Texture.class);
        manager.load("mainmenu.png", Texture.class);
        manager.load("bgm.ogg", Music.class);
        manager.load("battery.png", Texture.class);
        manager.load("battery.wav", Sound.class);
        manager.load("battery10.wav", Sound.class);
        manager.load("player.png", Texture.class);
        manager.load("player-right.png", Texture.class);
        manager.load("player-down.png", Texture.class);
        manager.load("player-left.png", Texture.class);
        manager.load("spawn1.wav", Sound.class);
        manager.load("spawn2.wav", Sound.class);
        manager.load("spawn3.wav", Sound.class);
        manager.load("spawn4.wav", Sound.class);
        manager.load("gameover.png", Texture.class);
        manager.load("solarpanel.png", Texture.class);
        manager.load("solar.wav", Sound.class);
        manager.load("gas.png", Texture.class);
        manager.load("chip.png", Texture.class);
        manager.load("gas.wav", Sound.class);
        manager.load("efficient.wav", Sound.class);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(manager.update()) {
            game.setScreen(new MainMenu(game));
            dispose();
        } else {
            manager.finishLoadingAsset("splash.png");
            game.getBatch().setProjectionMatrix(cam.combined);

            game.getBatch().begin();
            game.getBatch().draw(manager.get("splash.png", Texture.class), 0, 0, cam.viewportWidth, cam.viewportHeight);
            game.getBatch().end();

        }
    }

    @Override
    public void resize(int width, int height) {

    }

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
    public void dispose() { manager.unload("splash.png");}
}
