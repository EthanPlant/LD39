package com.exedo.ld.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.exedo.ld.LudumDare;

public class SplashScreen implements Screen {
    private LudumDare game;
    private OrthographicCamera cam;
    private Texture texture;

    private float timeCount;

    public SplashScreen(LudumDare game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1920, 1080);
        timeCount = 0;

        texture = new Texture(Gdx.files.internal("splash.png"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        timeCount += delta;
        if(timeCount >= 2) {
            game.setScreen(new MainMenu(game));
            dispose();
        } else {
            game.getBatch().setProjectionMatrix(cam.combined);

            game.getBatch().begin();
            game.getBatch().draw(texture, 0, 0, cam.viewportWidth, cam.viewportHeight);
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
    public void dispose() {
        texture.dispose();
    }
}
