package sortingFactoryRelated;

import java.awt.*;
import java.util.ArrayList;

public class SortingZone {
    private Map map;
    private int width;
    private int height;
    private ArrayList<SortingRobot> sortingRobots;
    private ArrayList<SortingComponent> sortingComponents;

    public SortingZone(int width, int height) {
        this.map = new Map(width, height);
        this.width = width;
        this.height = height;
    }


    public SortingComponent getSortingComponent(int ID) {
        return this.sortingComponents.get(ID);
    }

    public void addSortingComponent(SortingComponent sortingComponent) {
        this.sortingComponents.add(sortingComponent);
    }

    public void removeSortingComponent(int ID) {
        this.sortingComponents.remove(ID);
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



}
