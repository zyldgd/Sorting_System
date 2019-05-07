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
        this.sortingRobots = new ArrayList<>();
        PutDownStation putDownStation0 = new PutDownStation(new Point(4,8), this);
        PutDownStation putDownStation1 = new PutDownStation(new Point(8,8), this);
        PutDownStation putDownStation2 = new PutDownStation(new Point(12,8), this);
        PutDownStation putDownStation3 = new PutDownStation(new Point(16,8), this);
        this.map.addComponent("putDownStation" , 201,putDownStation0);
        this.map.addComponent("putDownStation" , 202,putDownStation1);
        this.map.addComponent("putDownStation" , 203,putDownStation2);
        this.map.addComponent("putDownStation" , 204,putDownStation3);

        PickUpStation pickUpStation0 = new PickUpStation(new Point(2,2), this);
        PickUpStation pickUpStation1 = new PickUpStation(new Point(6,2), this);
        PickUpStation pickUpStation2 = new PickUpStation(new Point(10,2), this);
        PickUpStation pickUpStation3 = new PickUpStation(new Point(14,2), this);
        this.map.addComponent("pickUpStation" , 101,pickUpStation0);
        this.map.addComponent("pickUpStation" , 102,pickUpStation1);
        this.map.addComponent("pickUpStation" , 103,pickUpStation2);
        this.map.addComponent("pickUpStation" , 104,pickUpStation3);

        SortingRobot robot0 = new SortingRobot(new Point(1, 1), this);
        SortingRobot robot1 = new SortingRobot(new Point(4, 6), this);
        SortingRobot robot2 = new SortingRobot(new Point(5, 13), this);
        SortingRobot robot3 = new SortingRobot(new Point(3, 8), this);

        robot0.setPickUpID(101);
        robot1.setPickUpID(102);
        robot2.setPickUpID(103);
        robot3.setPickUpID(104);

        this.addSortingRobot(robot0);
        this.addSortingRobot(robot1);
        this.addSortingRobot(robot2);
        this.addSortingRobot(robot3);
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
