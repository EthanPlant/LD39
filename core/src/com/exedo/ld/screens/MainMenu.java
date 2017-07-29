package com.exedo.ld.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.exedo.ld.LudumDare;

public class MainMenu implements Screen{
    private LudumDare game;

    OrthographicCamera cam;

    private BitmapFont font;

    public MainMenu(LudumDare game) {
        this.game = game;

        font = new BitmapFont();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 640, 360);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        game.getBatch().setProjectionMatrix(cam.combined);

        game.getBatch().begin();
        font.draw(game.getBatch(), "Welcome to Recharge!", 250, 250);
        font.draw(game.getBatch(), "Created in 48 hours for Ludum Dare 39", 212, 50);
        font.draw(game.getBatch(), "Click anywhere to begin!", 250, 360/2);
        game.getBatch().end();

        if(Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
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
        font.dispose();
    }
}
