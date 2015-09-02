package com.selbo.map;

import com.selbo.display.GamePanel;
import com.selbo.sprite.Sprite;
import com.selbo.util.MapUtils;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Josh on 9/1/2015.
 */
public class SpriteMap {
    private Sprite[][] backgroundMap;
    private List<Sprite> objects;

    /**
     * Parse the given backgroundMap format into a 2D array of sprite and list of objects
     */
    public SpriteMap(char[][] mapCodes, Sprite... objects) {
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
        this.objects = new ArrayList<>(Arrays.asList(objects));
    }

    public Sprite[][] getBackgroundMap() {
        return backgroundMap;
    }

    public List<Sprite> getObjects() {
        return objects;
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

        for (Sprite object : objects) {
            object.paint(g2);
        }
    }
}
