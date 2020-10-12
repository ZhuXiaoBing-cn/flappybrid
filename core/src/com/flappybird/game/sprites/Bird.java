package com.flappybird.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
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
    private Rectangle bounds;
    private Texture birdCombination;
//    private Texture bird;
    private Animation birdAnimation;
    private Sound flag;

    public Bird(float x, float y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
//        bird = new Texture("bird.png");
        birdCombination = new Texture("birdanimation.png");
        flag = Gdx.audio.newSound(Gdx.files.internal("flap.ogg"));
        birdAnimation = new Animation(new TextureRegion(birdCombination), 3, 0.5f);
        bounds = new Rectangle(x, y, birdCombination.getWidth() / 3, birdCombination.getHeight());
    }

    public void update(float dt) {
        birdAnimation.update(dt);
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);

        position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y <= 0) {
            position.y = 0;
        }
        velocity.scl(1 / dt);

        bounds.setPosition(position.x, position.y);
    }

    public void dispose() {
        birdCombination.dispose();
        flag.dispose();
    }

    public void jump() {
        velocity.y = 250;
        flag.play(0.2f);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
//        return bird;
        return birdAnimation.getFrame();
    }
}
