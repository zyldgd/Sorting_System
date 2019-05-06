package sortingFactoryRelated;

import java.awt.*;
import java.util.ArrayList;

public class SortingZone {
    private Map map;
    private int scale;
    private int width;
    private int height;
    private ArrayList<SortingRobot> sortingRobots;

    public SortingZone(int width, int height) {
        this.map = new Map(width, height);
        this.scale = 100;// 100/route
        this.width = width * this.scale;
        this.height = height * this.scale;
        PutDownStation putDownStation0 = new PutDownStation(new Point(5,5), this);
        this.map.addComponent("putDownStation" , 201,putDownStation0);
        PickUpStation pickUpStation0 = new PickUpStation(new Point(2,2), this);
        this.map.addComponent("pickUpStation" , 101,pickUpStation0);
    }

    public SortingRobot getSortingRobot(int ID) {
        return this.sortingRobots.get(ID);
    }

    public void addSortingRobot(SortingRobot sortingRobot) {
        this.sortingRobots.add(sortingRobot);
    }

    public Map getMap() {
        return this.map;
    }


    public int getScale() {
        return scale;
    }

    public ArrayList<SortingRobot> getSortingRobots( ) {
        return this.sortingRobots;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
