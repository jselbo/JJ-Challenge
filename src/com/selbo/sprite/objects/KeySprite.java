package com.selbo.sprite.objects;

import com.selbo.sprite.Animation;
import com.selbo.sprite.Sprite;
import com.selbo.util.ImageUtils;

/**
 * Created by Josh on 9/1/2015.
 */
public class KeySprite extends Sprite {
    public KeySprite() {
        super(createAnimations());
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    private static Animation[] createAnimations() {
        Animation[] anims = new Animation[] {
                new Animation().addFrame(ImageUtils.loadImage("key-red.png"), 0)
        };
        return anims;
    }
}
