package com.selbo.display;

import com.selbo.map.SpriteMap;
import com.selbo.sprite.Direction;
import com.selbo.sprite.objects.HeroSprite;
import com.selbo.util.MapUtils;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Josh on 9/1/2015.
 */
public class GamePanel extends JPanel {
    public static final int BLOCK_SIZE = 32;
    public static final int NUM_ROWS = 18, NUM_COLS = 24;

    // Attempt to update game at 60fps
    private static final float TARGET_FPS = 30f;
    // (1 second) divided by (frames per second)
    private static final int SLEEP_TIME = (int)(1000f / TARGET_FPS);
    private static final Dimension PREFERRED_SIZE = new Dimension(NUM_COLS * BLOCK_SIZE, NUM_ROWS * BLOCK_SIZE);

    private SpriteMap map;
    private HeroSprite hero;

    private Thread gameThread;
    private boolean running;

    private int pressedKey;
    private int handledKey;

    public GamePanel() {
        setBackground(Color.BLACK);
        setPreferredSize(PREFERRED_SIZE);

        map = MapUtils.createLevel1Map();
        hero = new HeroSprite();

        gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                gameLoop();
            }
        });

        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                pressedKey = e.getKeyCode();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pressedKey = 0;
                handledKey = 0;
            }
        });
    }

    public void startGame() {
        pressedKey = 0;
        handledKey = 0;

        running = true;
        gameThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        map.paint(g2);
        hero.paint(g2);
    }

    private void gameLoop() {
        long previousTime = System.currentTimeMillis();

        while (running) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - previousTime;
            previousTime = currentTime;

            updateGame(elapsedTime);

            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException ie) {}
        }
    }

    private void updateGame(long elapsedTime) {
        handleKeyInput();

        map.update(elapsedTime);
        hero.update(elapsedTime);
        paintImmediately(0, 0, getWidth(), getHeight());
    }

    private void handleKeyInput() {
        if (pressedKey > 0) {
            if (handledKey != pressedKey) {
                HeroSprite.State direction = null;
                int blockX = hero.getBlockX();
                int blockY = hero.getBlockY();
                switch (pressedKey) {
                    case KeyEvent.VK_UP:
                        direction = HeroSprite.State.UP;
                        blockY--;
                        break;
                    case KeyEvent.VK_RIGHT:
                        direction = HeroSprite.State.RIGHT;
                        blockX++;
                        break;
                    case KeyEvent.VK_DOWN:
                        direction = HeroSprite.State.DOWN;
                        blockY++;
                        break;
                    case KeyEvent.VK_LEFT:
                        direction = HeroSprite.State.LEFT;
                        blockX--;
                        break;
                }

                boolean canMove =
                        (blockX >= 0 && blockX < NUM_COLS)
                        && (blockY >= 0 && blockY < NUM_ROWS)
                        && map.getBackgroundMap()[blockY][blockX].isPassable();
                if (direction != null && canMove) {
                    hero.setHeroState(direction);
                    hero.setBlockPosition(blockX, blockY);
                }

                // TODO check object (key) and do something

                handledKey = pressedKey;
            }
        }
    }
}
