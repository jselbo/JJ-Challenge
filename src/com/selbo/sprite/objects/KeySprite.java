package com.selbo.sprite.objects;

import com.selbo.map.KeyPair;
import com.selbo.sprite.Animation;
import com.selbo.sprite.Sprite;
import com.selbo.util.ImageUtils;

/**
 * Created by Josh on 9/1/2015.
 */
public class KeySprite extends Sprite {
    private KeyPair.Type type;
    private boolean obtained;

    public KeySprite(KeyPair.Type type) {
        super(createAnimations(type));

        this.type = type;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    public boolean isObtained() {
        return obtained;
    }

    public void setObtained(boolean obtained) {
        this.obtained = obtained;
    }

    private static Animation[] createAnimations(KeyPair.Type type) {
        return new Animation[] {
                new Animation().addFrame(ImageUtils.loadImage("key-" + type.name().toLowerCase() + ".png"), 0)
        };
    }
}
