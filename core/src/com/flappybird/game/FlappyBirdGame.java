package com.flappybird.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappybird.game.screen.MenuScreen;


public class FlappyBirdGame extends Game {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	public static final String TITLE = "FlappyBrid";

	private SpriteBatch batch;
	private BitmapFont bitmapFont;
	
	@Override
	public void create () {

		batch = new SpriteBatch();
		bitmapFont = new BitmapFont();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		bitmapFont.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public BitmapFont getBitmapFont() {
		return bitmapFont;
	}

}
