package com.exedo.ld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exedo.ld.screens.SplashScreen;

public class LudumDare extends Game {
	public static final int WIDTH = 640;
	public static final int HEIGHT = 360;
	public static final float PPM = 100;

	public static FPSLogger fps;

	public static int score;
	public static int minutes;
	public static int seconds;

	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		fps = new FPSLogger();
		setScreen(new SplashScreen(this));
		Gdx.graphics.setTitle("Recharged");
	}

	@Override
	public void render() {
		super.render();
	}

	public SpriteBatch getBatch() {return batch; }

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}
}
