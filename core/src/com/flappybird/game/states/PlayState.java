package com.flappybird.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    private Bird bird;
    private Texture bg;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 200);
        bg = new Texture("bg.png");
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
        bird.update(dt);

        camera.position.x = bird.getPosition().x + 80;

        for (Tube tube : tubes) {
            System.out.println("camera.position.x:" + camera.position.x);
            System.out.println("tube.getPosTopTube().x + tube.getTopTube().getWidth():"
                    + tube.getPosTopTube().x + tube.getTopTube().getWidth());

            if (camera.position.x - (camera.viewportWidth / 2) >
                    tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));

            }
        }

        camera.update();

    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.end();
    }

    @Override
    protected void dispose() {
        bird.getBird().dispose();
        for (Tube tube :tubes){
            tube.getTopTube().dispose();
            tube.getBottomTube().dispose();
        }
    }
}
