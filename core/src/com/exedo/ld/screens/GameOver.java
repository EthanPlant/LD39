package com.exedo.ld.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.exedo.ld.LudumDare;

public class GameOver implements Screen{
    private LudumDare game;

    OrthographicCamera cam;

    BitmapFont font;

    String str;
    GlyphLayout layout;

    public GameOver(LudumDare game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1920, 1080);

        font = new BitmapFont(Gdx.files.internal("gameoverfont.fnt"), false);

        str = "You lasted " + LudumDare.minutes + ":" + String.format("%02d", LudumDare.seconds) + " and gathered " + LudumDare.score + " batteries.";
        layout = new GlyphLayout(font, str);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().setProjectionMatrix(cam.combined);

        game.getBatch().begin();
        game.getBatch().draw(SplashScreen.manager.get("gameover.png", Texture.class), 0, 0, cam.viewportWidth, cam.viewportHeight);
        font.draw(game.getBatch(), str, cam.viewportWidth / 2 - layout.width / 2, 500);
        game.getBatch().end();

            if(Gdx.input.isTouched()) {
                game.setScreen(new GameScreen(game));
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

    }
}
