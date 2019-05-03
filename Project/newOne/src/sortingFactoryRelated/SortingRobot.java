package sortingFactoryRelated;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;


public class SortingRobot extends SortingComponent implements Runnable, Routable {
    /* 50 FPS */
    final int FPS = 50;
    final int delay = 1000 / this.FPS;


    private int speed;
    private int spin;
    private Direction direction;
    private int process;
    private int efficiency;


    private Pack pack;
    private Queue<Direction> routes;
    private int power;// 当前电量
    private int capacitance;// 电容量

    public SortingRobot(int ID, SortingZone zone) {
        this.ID = ID;
        this.power = 500;
        this.capacitance = 500;
        this.dependOnSortingZone = zone;
        this.speed = this.dependOnSortingZone.getScale() / 10;   // 10 p/FPS
        this.spin = 5; // 5 degree/FPS
        this.direction = Direction.UP;
        this.degree = 0;
        this.process = 0;
    }


    private boolean initRoutes(Point origination, Point destination) {
        if (this.dependOnSortingZone == null)
            return false;
        this.routes.clear();
        Queue<Direction> lines = this.routeSearch(origination, destination);
        this.routes = lines;
        return true;
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

    private void charing() {

    }

    private void turn(Direction dir) {
        if (this.direction != dir) {
            int degreeTemp = 0;
            int degreeErr = 0;
            switch (dir) {
                case UP:
                    degreeTemp = 0;
                    break;
                case DOWN:
                    degreeTemp = 180;
                    break;
                case LEFT:
                    degreeTemp = 270;
                    break;
                case RIGHT:
                    degreeTemp = 90;
                    break;
            }
            while (this.degree != degreeTemp) {
                degreeErr = (degreeTemp - this.degree > 0) ? this.spin : -this.spin;
                if (Math.abs(degreeTemp - this.degree) > 180) {
                    degreeErr = -degreeErr;
                }
                this.degree += degreeErr;
                if (degree < 0) {
                    this.degree += 360;
                } else if (degree >= 360) {
                    this.degree -= 360;
                }
                System.out.println(this.degree);

                try {
                    Thread.sleep(delay);// 50FPS
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                }
            }
            this.direction = dir;
        }
    }

    private void move(Direction dir) {
        Point p = new Point(this.position);
        Route.setAsNextPoint(p, dir, this.dependOnSortingZone.getScale());
        while (!this.position.equals(p)) {
            System.out.println(this.position);
            Route.setAsNextPoint(this.position, dir, this.speed);
            try {
                Thread.sleep(delay);// 50FPS
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        }

    }

    private void stop(){
        try {
            Thread.sleep(delay);// 50FPS
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }
    }

    private void goToPickUpStation(int pickUpStationID) {
        Point p = this.dependOnSortingZone.getSortingComponent(pickUpStationID).getLocation();
        this.initRoutes(this.getLocation(), p);
        for (Direction dir : this.routes) {
            turn(dir);

            move(dir);
        }
    }

    private void goToPutDownStation(int putDownStationID) {
        Point p = this.dependOnSortingZone.getSortingComponent(putDownStationID).getLocation();
        this.initRoutes(this.getLocation(), p);
    }

    private void goToCharingStation(int charingStationID) {
        Point p = this.dependOnSortingZone.getSortingComponent(charingStationID).getLocation();
        this.initRoutes(this.getLocation(), p);
    }

    private void act() {
        System.out.println(String.format("robot[%d] is running!", this.ID));
        try {
            // 检查当前是否持有包裹
            if (this.pack == null) {
                this.goToPickUpStation(101);
            } else {

            }
            this.setPosition(new Point(1, 1));

        } catch (Exception e) {
            e.printStackTrace();
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
                    while (map.isPassable(originationTemp, dir) && Map.getDistance(originationTemp, destination, dir) > 0) {
                        Route.setAsNextPoint(originationTemp, dir, 1);
                        routesTemp.add(dir);
                    }
                } else {
                    Route r = map.getRoute(originationTemp);
                    // 检查是否只有唯一通路
                    if (r.numberOfPassable() == 1) {
                        dir = r.getOnePassableDirection();
                        Route.setAsNextPoint(originationTemp, dir, 1);
                        routesTemp.add(dir);
                    } else {
                        dir = r.getOtherPassableDirection(dir);
                        Route.setAsNextPoint(originationTemp, dir, 1);
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
        SortingZone zone = new SortingZone(6, 6);
        SortingRobot robot = new SortingRobot(1, zone);

        robot.setLocation(new Point(0, 0));
        robot.setPosition(new Point(0, 0));

        //robot.move(Direction.UP);
        robot.turn(Direction.DOWN);
        System.out.println("---------------------");
        robot.turn(Direction.UP);
        System.out.println("---------------------");

    }
}
