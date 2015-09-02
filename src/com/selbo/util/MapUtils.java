package com.selbo.util;

import com.selbo.display.GamePanel;
import com.selbo.map.SpriteMap;
import com.selbo.sprite.Sprite;
import com.selbo.sprite.objects.FloorSprite;
import com.selbo.sprite.objects.KeySprite;
import com.selbo.sprite.objects.KeyholeSprite;
import com.selbo.sprite.objects.WallSprite;

import java.util.ArrayList;

/**
 * Created by Josh on 9/1/2015.
 */
public class MapUtils {
    public static SpriteMap createLevel1Map() {
        return new SpriteMap(
                validate(new char[][] {
                    "FFFFFFFFFFFFFFFFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFFFFFFFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFFFFFFFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFFFFFFFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFWFFFFFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFWWWWWFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFWFFFWFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFWFFFWFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFWFFFFFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFWFFFFFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFFFFFFFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFFFFFFFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFFFFFFFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFFFFFFFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFFFFFFFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFFFFFFFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFFFFFFFFFFFFFFF".toCharArray(),
                    "FFFFFFFFFFFFFFFFFFFFFFFF".toCharArray(),
                }),
                new KeySprite().setBlockPosition(20, 8)
        );
    }

    public static Sprite createSprite(char tileCode) {
        switch (tileCode) {
            case 'F':
                return new FloorSprite();
            case 'H':
                return new KeyholeSprite();
            case 'W':
                return new WallSprite();
        }
        return null;
    }

    /**
     * Checks that the given map has the correct dimensions.
     * @param map the map to validate
     * @return the same map, if it passes validation
     */
    private static char[][] validate(char[][] map) {
        if (map.length != GamePanel.NUM_ROWS) {
            throw new IllegalStateException(String.format(
                    "Expected map to have %d rows. Found %d\n", GamePanel.NUM_ROWS, map.length));
        }
        for (int i = 0; i < map.length; i++) {
            char[] row = map[i];
            if (row.length != GamePanel.NUM_COLS) {
                throw new IllegalStateException(String.format(
                        "Expected map row %d to have %d cols. Found %d\n", i, GamePanel.NUM_COLS, row.length));
            }
        }
        return map;
    }
}
