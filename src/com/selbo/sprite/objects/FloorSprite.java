package com.selbo.sprite.objects;

import com.selbo.sprite.Animation;
import com.selbo.sprite.Sprite;
import com.selbo.util.ImageUtils;

/**
 * Created by Josh on 9/1/2015.
 */
public class FloorSprite extends Sprite {
    public FloorSprite() {
        super(createAnimations());
    }

    private static Animation[] createAnimations() {
        Animation[] anims = new Animation[] {
                new Animation().addFrame(ImageUtils.loadImage("floor.png"), 0)
        };
        return anims;
    }

    @Override
    public boolean isPassable() {
        return true;
    }
}
