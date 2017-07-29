package com.exedo.ld.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.exedo.ld.LudumDare;

public class GameOver implements Screen{
    private LudumDare game;

    OrthographicCamera cam;

    private BitmapFont font;

    public GameOver(LudumDare game) {
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

        game.getBatch().begin();
        font.draw(game.getBatch(), "Game over!", 275, 250);
        font.draw(game.getBatch(), "Click anywhere to play again!", 212, 360/2);
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
