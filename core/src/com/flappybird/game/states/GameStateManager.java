package com.flappybird.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * @Classname GameStateManager
 * @Description TODO
 * @Date 2020/10/11 17:14
 * @Author by ZhuXiaoBing
 */
public class GameStateManager {


    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

}
