package com.selbo.sprite.objects;

import com.selbo.sprite.Animation;
import com.selbo.sprite.Sprite;
import com.selbo.util.ImageUtils;

/**
 * Created by Josh on 9/1/2015.
 */
public class HeroSprite extends Sprite {
    public enum State {
        UP, RIGHT, DOWN, LEFT;
    }

    public HeroSprite() {
        super(createAnimations());
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    public void setHeroState(HeroSprite.State state) {
        setState(state.ordinal());
    }

    private static Animation[] createAnimations() {
        return new Animation[] {
                new Animation().addFrame(ImageUtils.loadImage("hero-up.png"), 0),
                new Animation().addFrame(ImageUtils.loadImage("hero-right.png"), 0),
                new Animation().addFrame(ImageUtils.loadImage("hero-down.png"), 0),
                new Animation().addFrame(ImageUtils.loadImage("hero-left.png"), 0),
        };
    }
}
