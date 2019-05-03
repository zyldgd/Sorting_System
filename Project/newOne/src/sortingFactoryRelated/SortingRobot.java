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


    @Override
    public Queue<Direction> routeSearch(Point origination, Point destination) {
        if (origination.equals(destination)) {
            return null;
        } else {
            Point originationTemp = new Point(origination);
            Queue<Direction> routesTemp = new LinkedList<Direction>();
            Map map = this.dependOnSortingZone.getMap();
            int height;
            int width;
            Direction dir;
            while (!originationTemp.equals(destination)) {
                height = originationTemp.y - destination.y;
                width = originationTemp.x - destination.x;
                // 求得最优移动方向
                if (Math.abs(height) > Math.abs(width)) {
                    dir = height > 0 ? Direction.DOWN : Direction.UP;
                } else {
                    dir = width > 0 ? Direction.LEFT : Direction.RIGHT;
                }
                // 检查最优方向是否可通行
                if (map.isPassable(originationTemp, dir)) {
                    while (map.isPassable(originationTemp, dir) && Map.getDistance(originationTemp,destination,dir)>0) {
                        Route.setAsNextPoint(originationTemp, dir);
                        routesTemp.add(dir);
                    }
                } else {
                    Route r = map.getRoute(originationTemp);
                    // 检查是否只有唯一通路
                    if (r.numberOfPassable() == 1) {
                        dir = r.getOnePassableDirection();
                        Route.setAsNextPoint(originationTemp, dir);
                        routesTemp.add(dir);
                    } else {
                        dir = r.getOtherPassableDirection(dir);
                        Route.setAsNextPoint(originationTemp, dir);
                        routesTemp.add(dir);
                    }
                }
            }
            return routesTemp;
        }

    }

    @Override
    public void run() {
        this.act();
    }


    public static void main(String[] args) {
        SortingRobot robot = new SortingRobot(1);
        SortingZone zone = new SortingZone(6, 6);
        robot.setDependOnSortingZone(zone);
        zone.getMap().print();
        Queue<Direction> lines =  robot.routeSearch(new Point(2, 1), new Point(0, 2));
        for (Direction D: lines) {
            System.out.println(D);
        }
        System.out.println(1);
    }
}
