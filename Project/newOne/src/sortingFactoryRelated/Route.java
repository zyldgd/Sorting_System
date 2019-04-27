package sortingFactoryRelated;

import java.awt.Point;

enum Direction{
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

    public Route(boolean up, boolean down, boolean left, boolean right){
        this.upOut = up;
        this.downOut = down;
        this.leftOut = left;
        this.rightOut = right;
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

}
