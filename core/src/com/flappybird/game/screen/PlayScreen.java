package com.flappybird.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flappybird.game.FlappyBirdGame;
import com.flappybird.game.sprites.Bird;
import com.flappybird.game.sprites.Tube;

/**
 * @Classname MenuScreen
 * @Description TODO
 * @Date 2020/10/15 19:39
 * @Author by ZhuXiaoBing
 */
public class PlayScreen extends ScreenAdapter {

    private final static int TUBE_COUNT = 4;
    private final static int TUBE_SPACING = 180;
    /**
     * 第一个管子生成的位置
     */
    private static final int TUBE_BEGIN_X = 300;

    private final static int GROUND_Y_OFFSET = -70;
    private FlappyBirdGame flappyBirdGame;
    private OrthographicCamera camera;
    private Bird bird;
    private Texture bg;
    private Array<Tube> tubes;
    private Tube firstTube;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Sound hit;

    public PlayScreen(FlappyBirdGame flappyBirdGame) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, FlappyBirdGame.WIDTH / 2, FlappyBirdGame.HEIGHT / 2);
        bird = new Bird();
        tubes = new Array<>();
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        hit = Gdx.audio.newSound(Gdx.files.internal("hit.ogg"));

        groundPos1 = new Vector2(camera.position.x - (camera.viewportWidth / 2), GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - (camera.viewportWidth / 2)) + ground.getWidth(), GROUND_Y_OFFSET);

        firstTube = new Tube(TUBE_BEGIN_X);
        tubes.add(firstTube);

        this.flappyBirdGame = flappyBirdGame;

        for (int i = 1; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(firstTube.getPosTopTube().x + i * (TUBE_SPACING)));
        }
    }

    @Override
    public void show() {
        System.out.println("Current play screen");
        System.out.println("Play screen init work");
    }

    public void update(float dt) {

        inputHandle();
        bird.move(dt);
        if (camera.position.x + camera.viewportWidth / 2 > groundPos2.x) {
            groundPos1.set(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
            groundPos2.set(groundPos1.x + ground.getWidth(), GROUND_Y_OFFSET);
        }
        camera.position.x = bird.getPosBird().x + 80;

        for (Tube tube : tubes) {
            if (camera.position.x - (camera.viewportWidth / 2) >
                    tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + TUBE_SPACING * TUBE_COUNT);
            }
            if (tube.collides(bird.getBirdBounds())) {
                hit.play(0.1f);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flappyBirdGame.setScreen(new PlayScreen(flappyBirdGame));
                break;
            }
        }

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        flappyBirdGame.getBatch().setProjectionMatrix(camera.combined);


        flappyBirdGame.getBatch().begin();
        flappyBirdGame.getBatch().draw(bg, camera.position.x - camera.viewportWidth / 2, 0);
        flappyBirdGame.getBatch().draw(ground, groundPos1.x, groundPos1.y);
        flappyBirdGame.getBatch().draw(ground, groundPos2.x, groundPos2.y);

        flappyBirdGame.getBatch().draw(bird.frame(delta), bird.getPosBird().x, bird.getPosBird().y,
                bird.getBirdAnimation().getKeyFrame(bird.getBirdAnimation().getFrameDuration()).getRegionWidth(),
                bird.getBirdAnimation().getKeyFrame(bird.getBirdAnimation().getFrameDuration()).getRegionHeight());

        for (Tube tube : tubes) {
            flappyBirdGame.getBatch().draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y,
                    tube.getTopTube().getWidth(), tube.getTopTube().getHeight());
            flappyBirdGame.getBatch().draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y,
                    tube.getBottomTube().getWidth(), tube.getBottomTube().getHeight());
        }

        flappyBirdGame.getBatch().end();
        update(delta);

    }

    public void inputHandle() {
        if (Gdx.input.justTouched()) {
            bird.flap();
        }
    }

    @Override
    public void resize(int width, int height) {
        System.out.println(width);
        System.out.println(height);
    }


    @Override
    public void dispose() {
        bird.dispose();
        bg.dispose();
        firstTube.dispsoe();
    }
}
