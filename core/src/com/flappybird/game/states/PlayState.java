package com.flappybird.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flappybird.game.FlappyBird;
import com.flappybird.game.sprites.Bird;
import com.flappybird.game.sprites.Tube;

/**
 * @Classname PlayState
 * @Description TODO
 * @Date 2020/10/11 18:03
 * @Author by ZhuXiaoBing
 */
public class PlayState extends State {

    private final static int TUBE_SPACING = 125;
    private final static int TUBE_COUNT = 4;
    private final static int GROUND_Y_OFFSET = -50;

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Sound hit;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(30, 250);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        hit = Gdx.audio.newSound(Gdx.files.internal("hit.ogg"));
        groundPos1 = new Vector2(camera.position.x - (camera.viewportWidth / 2), GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - (camera.viewportWidth / 2)) + ground.getWidth(), GROUND_Y_OFFSET);

        camera.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);

        tubes = new Array<>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);

        camera.position.x = bird.getPosition().x + 80;

        for (Tube tube : tubes) {

            if (camera.position.x - (camera.viewportWidth / 2) >
                    tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if (tube.collides(bird.getBounds())) {
                hit.play(0.2f);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("小鸟: "+"x:" + bird.getBounds().x + "   y:" + bird.getBounds().y);
//                System.out.println("上面管子: "+"x:" + tube.getPosTopTube().x+ "   y:" + tube.getPosTopTube().y);
//                System.out.println("下面管子: "+"x:" + tube.getPosBotTube().x+ "   y:" + tube.getPosBotTube().y);
                gsm.set(new PlayState(gsm));
                break;
            }
        }

        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            gsm.set(new PlayState(gsm));
        }

        camera.update();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.end();
    }

    private void updateGround() {
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }

        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }

    @Override
    protected void dispose() {
        bg.dispose();
        ground.dispose();
        bird.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("Play State Dispose.");
    }
}
