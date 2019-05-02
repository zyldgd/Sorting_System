package sortingFactoryRelated;

import java.awt.Point;

enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}


public class Route {
    private boolean upOut;
    private boolean downOut;
    private boolean leftOut;
    private boolean rightOut;


    public Route(boolean up, boolean down, boolean left, boolean right) {
        this.upOut = up;
        this.downOut = down;
        this.leftOut = left;
        this.rightOut = right;
    }

    public void setUpOut(boolean out) {
        this.upOut = out;
    }

    public void setDownOut(boolean out) {
        this.downOut = out;
    }

    public void setLeftOut(boolean out) {
        this.leftOut = out;
    }

    public void setRightOut(boolean out) {
        this.rightOut = out;
    }

    public boolean isUpOut() {
        return this.upOut;
    }

    public boolean isDownOut() {
        return this.downOut;
    }

    public boolean isLeftOut() {
        return this.leftOut;
    }

    public boolean isRightOut() {
        return this.rightOut;
    }

    public int numberOfPassable() {
        int sum = 0;
        sum = (this.upOut ? 1 : 0) + (this.downOut ? 1 : 0) + (this.leftOut ? 1 : 0) + (this.rightOut ? 1 : 0);
        return sum;
    }

    public Direction getOnePassableDirection() {
        if (this.upOut)
            return Direction.UP;
        else if (this.downOut)
            return Direction.DOWN;
        else if (this.leftOut)
            return Direction.LEFT;
        else if (this.rightOut)
            return Direction.RIGHT;
        return null;
    }

    public Direction getOtherPassableDirection(Direction dir) {
        if (dir.equals(Direction.UP) || dir.equals(Direction.DOWN)) {
            if (this.leftOut)
                return Direction.LEFT;
            else if (this.rightOut)
                return Direction.RIGHT;
        } else {
            if (this.upOut)
                return Direction.UP;
            else if (this.downOut)
                return Direction.DOWN;
        }
        return null;
    }

    public static void setAsNextPoint(Point point, Direction dir) {
        switch (dir) {
            case UP:
                point.y += 1;
                break;
            case DOWN:
                point.y -= 1;
                break;
            case LEFT:
                point.x -= 1;
                break;
            case RIGHT:
                point.x += 1;
                break;
        }

    }
}
