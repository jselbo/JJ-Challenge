package com.selbo.sprite.objects;

import com.selbo.map.KeyPair;
import com.selbo.sprite.Animation;
import com.selbo.sprite.Sprite;
import com.selbo.util.ImageUtils;

/**
 * Created by Josh on 9/1/2015.
 */
public class KeyholeSprite extends Sprite {
    public static final int STATE_LOCKED = 0;
    public static final int STATE_UNLOCKED = 1;

    private KeyPair.Type type;

    public KeyholeSprite(KeyPair.Type type) {
        super(createAnimations(type));

        this.type = type;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    private static Animation[] createAnimations(KeyPair.Type type) {
        return new Animation[] {
                new Animation().addFrame(ImageUtils.loadImage("keyhole-" + type.name().toLowerCase() + ".png"), 0),
                new Animation().addFrame(null, 0),
        };
    }
}
