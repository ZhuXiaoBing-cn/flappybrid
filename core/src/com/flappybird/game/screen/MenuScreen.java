package com.flappybird.game.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.flappybird.game.FlappyBirdGame;
import com.flappybird.game.sprites.Bird;

/**
 * @Classname MenuScreen
 * @Description TODO
 * @Date 2020/10/15 19:39
 * @Author by ZhuXiaoBing
 */
public class MenuScreen extends ScreenAdapter {

    private FlappyBirdGame flappyBirdGame;
    private OrthographicCamera camera;
    private Bird bird;
    private Texture bg, playbtn;
    private Texture title, ratebtn, scorebtn;
    private float frameTime;
    private static final int SCREEN_ID = 1;

    public MenuScreen(FlappyBirdGame flappyBirdGame) {
        bg = new Texture("bg.png");
        playbtn = new Texture("playbtn.png");
        title = new Texture("title.png");
        ratebtn = new Texture("ratebtn.png");
        scorebtn = new Texture("scorebtn.png");
        bird = new Bird(SCREEN_ID);
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
        //title
        flappyBirdGame.getBatch().draw(title, camera.viewportWidth / 2 - title.getWidth() / 2, camera.viewportHeight * 2 / 3,
                title.getWidth(), title.getHeight());
        //ratebtn
        flappyBirdGame.getBatch().draw(ratebtn, camera.viewportWidth / 2 - ratebtn.getWidth() / 2, camera.viewportHeight * 2 / 5,
                ratebtn.getWidth(), ratebtn.getHeight());

        //bird animation
        flappyBirdGame.getBatch().draw(bird.frame(delta), camera.viewportWidth / 2 - bird.frame(delta).getRegionWidth() / 2,
                camera.viewportHeight / 2, bird.frame(delta).getRegionWidth(), bird.frame(delta).getRegionHeight());
        //playbtn
        flappyBirdGame.getBatch().draw(playbtn, (camera.viewportWidth / 2 - playbtn.getWidth() / 2) - 150,
                camera.viewportHeight * 2 / 8, playbtn.getWidth(), playbtn.getHeight());

        //scorebtn
        flappyBirdGame.getBatch().draw(scorebtn, (camera.viewportWidth / 2 - scorebtn.getWidth() / 2) + 150,
                camera.viewportHeight * 2 / 8, scorebtn.getWidth(), scorebtn.getHeight());


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

        title.dispose();
        ratebtn.dispose();
        scorebtn.dispose();
        bg.dispose();
        playbtn.dispose();
    }
}
