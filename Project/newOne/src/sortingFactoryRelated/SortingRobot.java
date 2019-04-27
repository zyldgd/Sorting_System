package sortingFactoryRelated;

import java.awt.*;

public class SortingRobot implements Positionable {

    private int width;
    private int height;
    private float speed = 0.5f;  // 0.5m/s
    private float spin = 180;    // 180degree/s
    private float curAngle = 0;  // Clockwise


    public int ID;
    public Point position;
    public Point curCoordinates;

    public SortingRobot(int ID, int width, int height, Point point) {
        this.ID = ID;
        this.width = width;
        this.height = height;
        this.position = point;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }



    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public Boolean setPosition(Point p) {
        this.position = p;
        return true;
    }

}
