package com.javaLewis.tankwar;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x;
    private int y;
    private Direction direction;
    private boolean enemy;

    public Tank(int x, int y, Direction direction) {
        this(x,y,false,direction);
    }

    public Tank(int x, int y, boolean enemy, Direction direction) {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    void move() {
        if (this.stopped) return;
        switch (direction) {
            case UP:
                this.y -= 5;
                break;
            case DOWN:
                this.y += 5;
                break;
            case LEFT:
                this.x -= 5;
                break;
            case RIGHT:
                this.x += 5;
                break;
            case UPRIGHT:
                this.x += 5;
                this.y -= 5;
                break;
            case UPLEFT:
                this.x -= 5;
                this.y -= 5;
                break;
            case DOWNRIGHT:
                this.x += 5;
                this.y += 5;
                break;
            case DOWNLEFT:
                this.x -= 5;
                this.y += 5;
                break;
        }
    }

    Image getImage() {
        String prefix = enemy ? "e" : "";
        switch (direction) {
            case UP:
                return Tools.getImage(prefix + "tankU.gif");
            case DOWN:
                return Tools.getImage(prefix + "tankD.gif");
            case LEFT:
                return Tools.getImage(prefix + "tankL.gif");
            case RIGHT:
                return Tools.getImage(prefix + "tankR.gif");
            case UPRIGHT:
                return Tools.getImage(prefix + "tankRU.gif");
            case UPLEFT:
                return Tools.getImage(prefix + "tankLU.gif");
            case DOWNRIGHT:
                return Tools.getImage(prefix + "tankRD.gif");
            case DOWNLEFT:
                return Tools.getImage(prefix + "tankLD.gif");
        }
        return null;
    }

    void draw(Graphics g) {
        this.determineDirection();
        this.move();
        g.drawImage(this.getImage(), this.x, this.y, null);
    }

    private boolean stopped;

    private void determineDirection() {
        if (!up && !right && !down && !left) {
            this.stopped = true;
        } else {
            if (up && left && !down && !right) this.direction = Direction.UPLEFT;
            else if (up && !left && !down && right) this.direction = Direction.UPRIGHT;
            else if (up && !left && !down && !right) this.direction = Direction.UP;
            else if (!up && !left && down && !right) this.direction = Direction.DOWN;
            else if (!up && left && down && !right) this.direction = Direction.DOWNLEFT;
            else if (!up && !left && down && right) this.direction = Direction.DOWNRIGHT;
            else if (!up && left && !down && !right) this.direction = Direction.LEFT;
            else if (!up && !left && !down && right) this.direction = Direction.RIGHT;
            this.stopped = false;
        }
    }

    private boolean up, down, left, right;

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
        }
    }
}
