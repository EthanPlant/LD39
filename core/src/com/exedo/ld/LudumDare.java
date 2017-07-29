package com.exedo.ld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exedo.ld.screens.MainMenu;

public class LudumDare extends Game {
	public static final int WIDTH = 640;
	public static final int HEIGHT = 360;
	public static final float PPM = 100;

	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new MainMenu(this));
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
