package com.selbo.sprite;

import com.selbo.display.GamePanel;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class Sprite {
  protected Animation[] animations;
  protected int currIndex;

    protected Direction direction = Direction.RIGHT; // must be LEFT or RIGHT
    protected float x, y;
    protected float vx, vy; // pixels per millisecond

    protected int width, height; // set only by first image

    public Sprite(Animation[] animations) {
      if (animations == null || animations.length == 0) {
        throw new IllegalArgumentException("There must be at least 1 animation for a sprite");
      }

      this.animations = animations;
      currIndex = 0;

      x = 0;
      y = 0;
      vx = 0;
      vy = 0;
    }

    public abstract boolean isPassable();

    public void setState(int index) {
      if (index != currIndex && index >= 0 && index < animations.length) {
        x += (animations[currIndex].getImage().getWidth(null) - animations[index].getImage().getWidth(null))/2;
            y += animations[currIndex].getImage().getHeight(null) - animations[index].getImage().getHeight(null);

        currIndex = index;

        animations[currIndex].start();
      }
    }

    public int getState() {
      return currIndex;
    }

    public Animation getAnimation() {
      return animations[currIndex];
    }

    // Allow subclasses access to all animations
    protected Animation[] getAnimations() {
      return animations;
    }

    public void update(long elapsedTime) {
        x += vx * elapsedTime;
        y += vy * elapsedTime;

        int initialFrame = animations[currIndex].getCurrentIndex();
        animations[currIndex].update(elapsedTime);
        int finalFrame = animations[currIndex].getCurrentIndex();

        if (initialFrame != finalFrame
                && animations[currIndex].getImage() != null
                && animations[currIndex].getImage(initialFrame) != null) {
            width = animations[currIndex].getImage().getWidth(null);
            height = animations[currIndex].getImage().getHeight(null);

            x += (animations[currIndex].getImage(initialFrame).getWidth(null) - width)/2;
            y += animations[currIndex].getImage(initialFrame).getHeight(null) - height;
        }
    }

    public void paint(Graphics2D g2) {
      Image image = animations[currIndex].getImage();
      if (image != null) {
        if (direction == Direction.RIGHT) {
            g2.drawImage(image, (int) x, (int) y, null);
        } else {
            g2.drawImage(image, (int) x + image.getWidth(null), (int) y, (int) x, (int) y + image.getHeight(null),
                    0, 0, image.getWidth(null), image.getHeight(null), null);
        }
      }
    }

    public Sprite setPosition(float x, float y) {
      this.x = x;
      this.y = y;
      return this;
    }

    public Sprite setPosition(Point2D location) {
      if (location != null) {
        this.x = (float)location.getX();
        this.y = (float)location.getY();
      }
      return this;
    }

    public Sprite setBlockPosition(int x, int y) {
        setBlockX(x);
        setBlockY(y);
        return this;
    }

    public Point getBlockPosition() {
        return new Point(getBlockX(), getBlockY());
    }

    public Sprite setBlockX(int x) {
        this.x = x * GamePanel.BLOCK_SIZE;
        return this;
    }

    public int getBlockX() {
        return (int) (x / GamePanel.BLOCK_SIZE);
    }

    public Sprite setBlockY(int y) {
        this.y = y * GamePanel.BLOCK_SIZE;
        return this;
    }

    public int getBlockY() {
        return (int) (y / GamePanel.BLOCK_SIZE);
    }

    public Point2D getPosition() {
      return new Point2D.Float(x, y);
    }

    public Sprite setX(float x) {
        this.x = x;
        return this;
    }

    public float getX() {
        return x;
    }

    public Sprite setY(float y) {
        this.y = y;
        return this;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
      return animations[currIndex].getImage().getWidth(null);
    }

    public int getHeight() {
      return animations[currIndex].getImage().getHeight(null);
    }

    public Rectangle2D getBounds() {
      return new Rectangle2D.Float(x, y, width, height);
    }

    public void setVelocityX(float vx) {
        this.vx = vx;
    }

    public float getVelocityX() {
        return vx;
    }

    public void setVelocityY(float vy) {
        this.vy = vy;
    }

    public float getVelocityY() {
        return vy;
    }

    public Sprite setDirection(Direction direction) {
      if (direction == Direction.RIGHT || direction == Direction.LEFT) {
          this.direction = direction;
      }
      return this;
    }

    public Direction getDirection() {
      return direction;
    }

    public Image getImage() {
        return animations[currIndex].getImage();
    }

    @Override
    public String toString() {
        return String.format("Sprite {x: %f, y: %f, vx: %f, vy: %f, animation index: %d}",
                x, y, vx, vy, currIndex);
    }
}