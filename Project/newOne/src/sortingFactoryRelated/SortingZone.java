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

    public boolean isPassable(Point point, Direction dir) {
        Point nextPoint = this.map.getNextPoint(point, dir);
        if (this.map.getRoute(nextPoint) != null) {
            boolean out = false;
            switch (dir) {
                case UP:
                    out = this.map.getRoute(point).isUpOut();
                    break;
                case DOWN:
                    out = this.map.getRoute(point).isDownOut();
                    break;
                case LEFT:
                    out = this.map.getRoute(point).isLeftOut();
                    break;
                case RIGHT:
                    out = this.map.getRoute(point).isRightOut();
                    break;
            }
            // 路径存在 且 没有障碍物
            return out && (this.map.getComponent(nextPoint) == null ||
                    this.map.getComponent(nextPoint) instanceof PickUpStation ||
                    this.map.getComponent(nextPoint) instanceof ChargingStation);
        }
        return false;
    }

}
