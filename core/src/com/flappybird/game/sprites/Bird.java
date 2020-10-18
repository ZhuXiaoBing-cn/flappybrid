package com.flappybird.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flappybird.game.FlappyBirdGame;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * @Classname Bird
 * @Description TODO
 * @Date 2020/10/11 18:50
 * @Author by ZhuXiaoBing
 */
public class Bird {
    /**
     * 鸟在X方向上的移动速度
     */
    private static final int MOVE_X = 100;
    /**
     * 鸟在Y方向上受到重力的影响
     */
    private int GRAVITY = 0;
    /**
     * 鸟在游戏中开始时X的位置
     */
    private static final int BIRD_BEGIN_X = 50;
    /**
     * 鸟在游戏中开始时Y的位置
     */
    private static final int BIRD_BEGIN_Y = 150;

    private float frameSumTime;
    private Sound wingsBeat;
    private Vector2 posBird;
    private Vector2 velocity;
    private Rectangle birdBounds;
    private Animation<TextureRegion> birdAnimation;
    private Array<Texture> array;
    private TextureAtlas atlas;

    public Bird(int currentScreenId) {
        if (currentScreenId == 1) {
            GRAVITY = 0;
        } else if (currentScreenId == 2) {
            GRAVITY = -15;
        }
        posBird = new Vector2(BIRD_BEGIN_X, BIRD_BEGIN_Y);
        velocity = new Vector2(0, 0);
        array = new Array<>();
        atlas = new TextureAtlas("bird.atlas");
        birdAnimation = new Animation<TextureRegion>(1 / 3f, atlas.findRegions("bird"), Animation.PlayMode.LOOP);
        wingsBeat = Gdx.audio.newSound(Gdx.files.internal("flap.ogg"));
        birdBounds = new Rectangle(posBird.x, posBird.y,
                birdAnimation.getKeyFrame(birdAnimation.getFrameDuration()).getRegionWidth(),
                birdAnimation.getKeyFrame(birdAnimation.getFrameDuration()).getRegionHeight());

    }

    public TextureRegion frame(float dt) {
        frameSumTime += dt;
        return birdAnimation.getKeyFrame(frameSumTime);
    }


    /**
     * 鸟的移动方法
     */
    public void move(float dt) {

        if (posBird.y > 0) {
            velocity.add(0, GRAVITY);
        }

        velocity.scl(dt);//dt小于1，velocity减小
        posBird.add(MOVE_X * dt, velocity.y);
        if (posBird.y <= 25) {
            posBird.y = 25;
        }

        velocity.scl(1 / dt);//dt大于1,增大
        birdBounds.setPosition(posBird.x, posBird.y);
    }

    /**
     * 鸟扇动翅膀方法
     */
    public void flap() {
        velocity.y = 250;
        wingsBeat.play(0.1f);
    }


    public Vector2 getPosBird() {
        return posBird;
    }

    public void dispose() {
        wingsBeat.dispose();
        for (Texture texture : array) {
            texture.dispose();
        }
    }

    public Rectangle getBirdBounds() {
        return birdBounds;
    }

    public Animation<TextureRegion> getBirdAnimation() {
        return birdAnimation;
    }
}
