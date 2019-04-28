package sortingFactoryRelated;

import java.awt.*;
import java.util.ArrayList;

public class SortingRobot extends SortingComponent implements Runnable {
    private int speed = 1;     // 1r/s
    private int spin = 180;    // 180degree/s

    private Pack pack;
    private ArrayList<Direction> routes;

    public SortingRobot(int ID) {
        this.ID = ID;
        routes = new ArrayList<Direction>();
    }

    private void act() {
        System.out.println(String.format("robot[%d] is running!", this.ID));
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.act();
    }

    private boolean initRoutes(Point origination, Point destination) {
        this.routes.clear();
        Point originationTemp = new Point(origination);
        Map m = this.dependOnSortingZone.getMap();
        Route curRoute = m.getRoute(origination);
        Direction priorDir = Direction.UP;
        if (curRoute != null) {
            while (!originationTemp.equals(destination)) {
                if (originationTemp.y < destination.y) {
                    if (this.dependOnSortingZone.isPassable(originationTemp, Direction.UP)) {
                        priorDir = Direction.UP;
                    } else {
                        if (originationTemp.x < destination.x) {
                            if (this.dependOnSortingZone.isPassable(originationTemp, Direction.RIGHT)) {
                                priorDir = Direction.RIGHT;
                            } else {
                                priorDir = Direction.LEFT;
                            }
                        } else {
                            if (this.dependOnSortingZone.isPassable(originationTemp, Direction.LEFT)) {
                                priorDir = Direction.LEFT;
                            } else {
                                priorDir = Direction.RIGHT;
                            }
                        }
                    }
                } else {
                    if (this.dependOnSortingZone.isPassable(originationTemp, Direction.DOWN)) {
                        priorDir = Direction.DOWN;
                    } else {
                        if (originationTemp.x < destination.x) {
                            if (this.dependOnSortingZone.isPassable(originationTemp, Direction.RIGHT)) {
                                priorDir = Direction.RIGHT;
                            } else {
                                priorDir = Direction.LEFT;
                            }
                        } else {
                            if (this.dependOnSortingZone.isPassable(originationTemp, Direction.LEFT)) {
                                priorDir = Direction.LEFT;
                            } else {
                                priorDir = Direction.RIGHT;
                            }
                        }
                    }
                }


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
        SortingZone zone = new SortingZone(20,20);
        robot.setDependOnSortingZone(zone);

        robot.initRoutes(new Point(0,0), new Point(10,10));



    }
}
