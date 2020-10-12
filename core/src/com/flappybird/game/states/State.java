package com.flappybird.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * @Classname State
 * @Description TODO
 * @Date 2020/10/11 17:04
 * @Author by ZhuXiaoBing
 */
public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;

    protected GameStateManager gsm;

    public State(GameStateManager gsm) {
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    protected abstract void update(float dt);
    protected abstract void render(SpriteBatch sb);
    protected abstract void dispose();

}
