package com.selbo.sprite.objects;

import com.selbo.sprite.Animation;
import com.selbo.sprite.Sprite;
import com.selbo.util.ImageUtils;

/**
 * Created by Josh on 9/1/2015.
 */
public class WallSprite extends Sprite {
    public WallSprite() {
        super(createAnimations());
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    private static Animation[] createAnimations() {
        Animation[] anims = new Animation[] {
                new Animation().addFrame(ImageUtils.loadImage("wall.png"), 0)
        };
        return anims;
    }
}
