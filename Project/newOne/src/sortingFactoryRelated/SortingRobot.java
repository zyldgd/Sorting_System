package sortingFactoryRelated;

import java.awt.*;
import java.util.*;

public class SortingRobot extends SortingComponent implements Runnable, Routable {
    private int speed = 1;     // 1r/s
    private int spin = 180;    // 180degree/s

    private Pack pack;
    private Queue<Direction> routes;

    public SortingRobot(int ID) {
        this.ID = ID;
    }

    private void act() {
        System.out.println(String.format("robot[%d] is running!", this.ID));
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private boolean initRoutes(Point origination, Point destination) {
        this.routes.clear();
        Point originationTemp = new Point(origination);
        Map m = this.dependOnSortingZone.getMap();
        Route curRoute = m.getRoute(origination);
        Direction priorDir = Direction.UP;
        if (curRoute != null) {
            while (!originationTemp.equals(destination)) {


                Route.setAsNextPoint(originationTemp, priorDir);
                this.routes.add(priorDir);
                System.out.println(originationTemp);
            }
        }
        return false;
    }

    private void loadPack(Pack pack) {
        if (this.pack == null) {
            this.pack = pack;
        }
    }

    private void unloadPack() {
        if (this.pack != null) {
            this.pack = null;
        }
    }

    public static void main(String[] args) {
        SortingRobot robot = new SortingRobot(1);
        SortingZone zone = new SortingZone(20, 20);
        robot.setDependOnSortingZone(zone);

        robot.initRoutes(new Point(0, 0), new Point(10, 10));


    }


    @Override
    public Queue<Direction> routeSearch(Point origination, Point destination) {
        Queue<Direction> routes = new LinkedList<Direction>();
        Set<Point> openedTable = new HashSet<Point>();
        Set<Point> closedTable = new HashSet<Point>();



        return null;
    }

    @Override
    public void run() {
        this.act();
    }
}
