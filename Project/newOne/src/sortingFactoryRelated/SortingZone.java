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
        this.width = width;
        this.height = height;
        this.scale = 100;// 100/route
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
}
