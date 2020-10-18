package com.flappybird.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * @Classname Tube
 * @Description TODO
 * @Date 2020/10/11 20:20
 * @Author by ZhuXiaoBing
 */
public class Tube {

    private static final int FLUCTUATION = 120;
    /**
     * 管子最低高度
     */
    private static final int LOW_LIMIT = 60;
    /**
     * 上下管子中间的间隔
     */
    private static final int TOP_BOTTOM_GAP = 120;


    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBottomTube;
    private Rectangle topBounds, botBounds;
    /**
     * 随机数是用于生成水管的高度
     */
    private Random random;

    public Tube(float x) {
        random = new Random();

        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");

        posTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TOP_BOTTOM_GAP + LOW_LIMIT);
        posBottomTube = new Vector2(x, posTopTube.y - TOP_BOTTOM_GAP - bottomTube.getHeight());

        topBounds = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        botBounds = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());

    }

    public boolean collides(Rectangle birdBounds) {
        if (birdBounds.overlaps(topBounds) || birdBounds.overlaps(botBounds)) {
            return true;
        }
        return false;
    }

    public void reposition(float x) {
        posTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TOP_BOTTOM_GAP + LOW_LIMIT);
        posBottomTube = new Vector2(x, posTopTube.y - TOP_BOTTOM_GAP - bottomTube.getHeight());

        topBounds = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        botBounds = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public void dispsoe() {
        topTube.dispose();
        bottomTube.dispose();
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }
}
