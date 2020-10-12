package com.flappybird.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappybird.game.FlappyBird;

/**
 * @Classname MenuState
 * @Description TODO
 * @Date 2020/10/11 17:43
 * @Author by ZhuXiaoBing
 */
public class MenuState extends State {


    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        sb.draw(playBtn, (FlappyBird.WIDTH / 2) - (playBtn.getWidth() / 2),
                (FlappyBird.HEIGHT / 2) - (playBtn.getHeight() / 2));
        sb.end();
    }

    @Override
    protected void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Dispose.");
    }
}
