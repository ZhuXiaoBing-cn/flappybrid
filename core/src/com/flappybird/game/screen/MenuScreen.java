package com.flappybird.game.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.flappybird.game.FlappyBirdGame;

/**
 * @Classname MenuScreen
 * @Description TODO
 * @Date 2020/10/15 19:39
 * @Author by ZhuXiaoBing
 */
public class MenuScreen extends ScreenAdapter {

    private FlappyBirdGame flappyBirdGame;
    private OrthographicCamera camera;
    private Texture bg, playbtn;

    public MenuScreen(FlappyBirdGame flappyBirdGame) {
        bg = new Texture("bg.png");
        playbtn = new Texture("playbtn.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, FlappyBirdGame.WIDTH, FlappyBirdGame.HEIGHT);
        this.flappyBirdGame = flappyBirdGame;
    }

    private void inputHandle() {
        if (Gdx.input.justTouched()) {
            flappyBirdGame.setScreen(new PlayScreen(flappyBirdGame));
        }
    }


    @Override
    public void show() {
        System.out.println("Current menu screen");
        System.out.println("Menu screen init work");
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        flappyBirdGame.getBatch().setProjectionMatrix(camera.combined);

        flappyBirdGame.getBatch().begin();
        flappyBirdGame.getBatch().draw(bg, 0, 0, camera.viewportWidth, camera.viewportHeight);
        flappyBirdGame.getBatch().draw(playbtn, (camera.viewportWidth / 2 - (playbtn.getWidth() / 2)),
                (camera.viewportHeight / 2 - (playbtn.getHeight() / 2)), playbtn.getWidth(), playbtn.getHeight());
        flappyBirdGame.getBitmapFont().getData().setScale(2.0f);
        flappyBirdGame.getBitmapFont().draw(flappyBirdGame.getBatch(), "Flappy  Bird", 175, 730);
        flappyBirdGame.getBitmapFont().getData().setScale(1.0f);

        flappyBirdGame.getBatch().end();

        inputHandle();
    }

    @Override
    public void resize(int width, int height) {
        System.out.println(width);
        System.out.println(height);
    }

    @Override
    public void pause() {

    }


    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bg.dispose();
        playbtn.dispose();
    }
}
