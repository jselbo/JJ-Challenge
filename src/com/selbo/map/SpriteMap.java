package com.selbo.map;

import com.selbo.sprite.Sprite;
import com.selbo.sprite.objects.KeySprite;
import com.selbo.sprite.objects.KeyholeSprite;
import com.selbo.util.MapUtils;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Josh on 9/1/2015.
 */
public class SpriteMap {
    private Sprite[][] backgroundMap;
    private Map<KeySprite, KeyholeSprite> keyMap;

    /**
     * Parse the given backgroundMap format into a 2D array of sprite and list of objects
     */
    public SpriteMap(char[][] mapCodes, KeyPair[] keyPairs) {
        backgroundMap = new Sprite[mapCodes.length][mapCodes[0].length];
        for (int row = 0; row < mapCodes.length; row++) {
            char[] rowArr = mapCodes[row];
            for (int col = 0; col < rowArr.length; col++) {
                char tileCode = rowArr[col];

                Sprite tile = MapUtils.createSprite(tileCode);
                tile.setBlockPosition(col, row);
                backgroundMap[row][col] = tile;
            }
        }

        keyMap = new HashMap<>(keyPairs.length);
        for (KeyPair pair : keyPairs) {
            KeySprite keySprite = new KeySprite(pair.type);
            keySprite.setBlockPosition(pair.key.blockX, pair.key.blockY);
            KeyholeSprite keyholeSprite = new KeyholeSprite(pair.type);
            keyholeSprite.setBlockPosition(pair.keyhole.blockX, pair.keyhole.blockY);

            keyMap.put(keySprite, keyholeSprite);
        }
    }

    public Sprite[][] getBackgroundMap() {
        return backgroundMap;
    }

    public void update(long elapsedTime) {
        // Do nothing for now
    }

    public void paint(Graphics2D g2) {
        for (Sprite[] tileRow : backgroundMap) {
            for (Sprite tile : tileRow) {
                tile.paint(g2);
            }
        }

        for (Map.Entry<KeySprite, KeyholeSprite> pair : keyMap.entrySet()) {
            KeySprite key = pair.getKey();
            if (!key.isObtained()) {
                key.paint(g2);
            }
            KeyholeSprite keyhole = pair.getValue();
            if (keyhole.getState() == KeyholeSprite.STATE_LOCKED) {
                keyhole.paint(g2);
            }
        }
    }

    public boolean isBlockPassable(int blockX, int blockY) {
        if (!backgroundMap[blockY][blockX].isPassable()) {
            return false;
        }

        for (Map.Entry<KeySprite, KeyholeSprite> pair : keyMap.entrySet()) {
            KeyholeSprite keyhole = pair.getValue();
            if (keyhole.getBlockX() == blockX
                    && keyhole.getBlockY() == blockY
                    && keyhole.getState() == KeyholeSprite.STATE_LOCKED) {
                if (pair.getKey().isObtained()) {
                    keyhole.setState(KeyholeSprite.STATE_UNLOCKED);
                    return true;
                }
                return false;
            }
        }

        return true;
    }

    public KeySprite getKeyOnBlock(int blockX, int blockY) {
        for (Map.Entry<KeySprite, KeyholeSprite> pair : keyMap.entrySet()) {
            KeySprite key = pair.getKey();
            if (key.getBlockX() == blockX
                    && key.getBlockY() == blockY
                    && !key.isObtained()) {
                return key;
            }
        }
        return null;
    }
}
