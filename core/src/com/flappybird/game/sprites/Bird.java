package com.flappybird.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * @Classname Bird
 * @Description TODO
 * @Date 2020/10/11 18:50
 * @Author by ZhuXiaoBing
 */
public class Bird {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Vector3 position;
    private Vector3 velocity;

    private Texture bird;

    public Bird(float x, float y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture("bird.png");
    }

    public void update(float dt) {

        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);

        position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y <= 0) {
            position.y = 0;
        }
        velocity.scl(1 / dt);
    }

    public void jump() {
        velocity.y = 250;
    }


    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird;
    }
}
