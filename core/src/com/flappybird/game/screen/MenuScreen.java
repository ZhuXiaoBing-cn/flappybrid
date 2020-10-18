package com.flappybird.game.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.flappybird.game.FlappyBirdGame;
import com.flappybird.game.sprites.Bird;

import javax.xml.stream.events.DTD;

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
    private Texture bg, bgnigth, playbtn, title, ratebtn, scorebtn, ground;
    private Rectangle playbtnBounds,ratebtnBounds,scorebtnBounds;


    //    private Vector2 groundPos1, groundPos2;
    private static final int SCREEN_ID = 1;

    public MenuScreen(FlappyBirdGame flappyBirdGame) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, FlappyBirdGame.WIDTH, FlappyBirdGame.HEIGHT);

        bg = new Texture("bg.png");
        playbtn = new Texture("playbtn.png");
        title = new Texture("title.png");
        ratebtn = new Texture("ratebtn.png");
        scorebtn = new Texture("scorebtn.png");
        ground = new Texture("ground.png");
        bgnigth = new Texture("bgnight.png");

        playbtnBounds = new Rectangle();
        ratebtnBounds = new Rectangle();
        scorebtnBounds = new Rectangle();


//        groundPos1 = new Vector2(camera.position.x - (camera.viewportWidth / 2), 0);
//        groundPos2 = new Vector2((camera.position.x - (camera.viewportWidth / 2)) + ground.getWidth(), 0);

        bird = new Bird(SCREEN_ID);

        this.flappyBirdGame = flappyBirdGame;
    }

    private void inputHandle() {

        int mouseX = Gdx.input.getX();
        int mouseY = FlappyBirdGame.HEIGHT-Gdx.input.getY();
        boolean leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT); // 鼠标左键触发事件
        if (leftPressed) {
//            System.out.println(mouseX+" "+mouseY);
            if(playbtnBounds.contains(mouseX,mouseY)){
                //点击开始游戏
                flappyBirdGame.setScreen(new PlayScreen(flappyBirdGame));
            }
            if(ratebtnBounds.contains(mouseX,mouseY)){
               //TODO 点击ratebtn
            }
            if(scorebtnBounds.contains(mouseX,mouseY)){
                //TODO 点击排行榜
            }


        }

//        if (Gdx.input.justTouched()) {
//            flappyBirdGame.setScreen(new PlayScreen(flappyBirdGame));
//        }
    }

    @Override
    public void show() {
        System.out.println("Current menu screen");
        System.out.println("Menu screen init work");
    }

//    //更新相机位置
//    public void update() {
//        camera.position.x = bird.getPosBird().x + 28;
//        if (camera.position.x + camera.viewportWidth / 2 > groundPos2.x) {
//            groundPos1.set(camera.position.x - camera.viewportWidth / 2, 0);
//            groundPos2.set(groundPos1.x + ground.getWidth(), 0);
//        }
//    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        //bird.move(delta);
//        update();
        flappyBirdGame.getBatch().setProjectionMatrix(camera.combined);

        flappyBirdGame.getBatch().begin();
        flappyBirdGame.getBatch().draw(bg, 0, 0, camera.viewportWidth, camera.viewportHeight);
        flappyBirdGame.getBatch().draw(bgnigth, camera.position.x, 0, camera.viewportWidth, camera.viewportHeight);

        //title
        flappyBirdGame.getBatch().draw(title, camera.viewportWidth / 2 - title.getWidth() / 2, camera.viewportHeight * 3 / 4,
                title.getWidth(), title.getHeight());

        //bird animation
        flappyBirdGame.getBatch().draw(bird.frame(delta), camera.viewportWidth / 2 - bird.frame(delta).getRegionWidth() / 2,
                camera.viewportHeight * 3 / 5, bird.frame(delta).getRegionWidth(), bird.frame(delta).getRegionHeight());

        //ratebtn
        flappyBirdGame.getBatch().draw(ratebtn, camera.viewportWidth / 2 - ratebtn.getWidth() / 2, camera.viewportHeight / 2,
                ratebtn.getWidth(), ratebtn.getHeight());


        //playbtn
        flappyBirdGame.getBatch().draw(playbtn, (camera.viewportWidth / 2 - playbtn.getWidth() / 2) - 150,
                camera.viewportHeight * 2 / 8, playbtn.getWidth(), playbtn.getHeight());

        //scorebtn
        flappyBirdGame.getBatch().draw(scorebtn, (camera.viewportWidth / 2 - scorebtn.getWidth() / 2) + 150,
                camera.viewportHeight * 2 / 8, scorebtn.getWidth(), scorebtn.getHeight());
        //ground
        flappyBirdGame.getBatch().draw(ground, 0, 0);
        flappyBirdGame.getBatch().draw(ground, ground.getWidth(), 0);

        flappyBirdGame.getBatch().end();

        playbtnBounds.set((camera.viewportWidth / 2 - playbtn.getWidth() / 2) - 150,
                camera.viewportHeight * 2 / 8, playbtn.getWidth(), playbtn.getHeight());
        ratebtnBounds.set(camera.viewportWidth / 2 - ratebtn.getWidth() / 2, camera.viewportHeight / 2,
                ratebtn.getWidth(), ratebtn.getHeight());
        scorebtnBounds.set((camera.viewportWidth / 2 - scorebtn.getWidth() / 2) + 150,
                camera.viewportHeight * 2 / 8, scorebtn.getWidth(), scorebtn.getHeight());

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
        bgnigth.dispose();
        title.dispose();
        ratebtn.dispose();
        scorebtn.dispose();
        bg.dispose();
        playbtn.dispose();
    }
}
