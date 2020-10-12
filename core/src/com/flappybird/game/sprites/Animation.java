package com.flappybird.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * @Classname Animation
 * @Description TODO
 * @Date 2020/10/12 20:31
 * @Author by ZhuXiaoBing
 */
public class Animation {

    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    private static final int IMAGE_SPACE = 3;

    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++) {
            if (i == 0) {
                frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
            }
            frames.add(new TextureRegion(region, i * frameWidth + IMAGE_SPACE, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount) {
            frame = 0;
        }
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }
}
