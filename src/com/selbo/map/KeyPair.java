package com.selbo.map;

/**
 * Created by Josh on 9/2/2015.
 */
public class KeyPair {
    public KeyPair.Key key;
    public KeyPair.Keyhole keyhole;
    public KeyPair.Type type;

    public KeyPair(KeyPair.Key key, KeyPair.Keyhole keyhole, KeyPair.Type type) {
        this.key = key;
        this.keyhole = keyhole;
        this.type = type;
    }

    public static class Key {
        public int blockX;
        public int blockY;

        public Key(int blockX, int blockY) {
            this.blockX = blockX;
            this.blockY = blockY;
        }
    }

    public static class Keyhole {
        public int blockX;
        public int blockY;

        public Keyhole(int blockX, int blockY) {
            this.blockX = blockX;
            this.blockY = blockY;
        }
    }

    public enum Type {
        BLUE,
        GREEN,
        RED,
        YELLOW,
    }
}
