package sortingFactoryRelated;

import java.awt.*;
import java.util.*;


public class SortingRobot extends SortingComponent implements Runnable, Routable {

    /* 50 FPS */
    final int FPS = 50;
    final int delay = 1000 / this.FPS;

    private int speed;
    private int spin;
    private Direction direction;
    private int loadProcess;
    private int unloadProcess;
    private int efficiency;

    private Pack pack;
    private Queue<Direction> routes;
    private int power;// 当前电量
    private int capacitance;// 电容量

    private Integer pickUpID;

    public Integer getPickUpID() {
        return pickUpID;
    }

    public void setPickUpID(Integer pickUpID) {
        this.pickUpID = pickUpID;
    }


    public SortingRobot(Point location, SortingZone dependOnSortingZone) {
        super(location, dependOnSortingZone);
        this.power = 500;
        this.capacitance = 500;
        this.dependOnSortingZone = dependOnSortingZone;
        this.speed = this.dependOnSortingZone.getScale() / 5;   // 10 p/FPS
        this.spin = 5; // 5 degree/FPS
        this.direction = Direction.UP;
        this.loadProcess = 0;
        this.unloadProcess = 0;
    }

    private boolean initRoutes(Point origination, Point destination) {
        if (this.dependOnSortingZone == null)
            return false;
        Queue<Direction> lines = this.routeSearch(origination, destination);
        this.routes = lines;
        return true;
    }

    /******************************** 行为方法 ********************************/

    private void loadPack(int pickUpStationID) {
        if (this.pack == null) {
            this.loadProcess = 0;
            PickUpStation pickUpStation = (PickUpStation) this.dependOnSortingZone.getMap().getComponent("pickUpStation", pickUpStationID);
            this.pack = pickUpStation.getPack();
            while (this.loadProcess < 100) {
                this.loadProcess += 5;
                this.stop();
            }
        }
    }

    private void unloadPack() {
        if (this.pack != null) {
            this.loadProcess = 0;
            this.pack = null;
            while (this.loadProcess < 100) {
                this.loadProcess += 5;
                this.stop();
            }
        }

    }

    private void charging() {


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
                // System.out.println(this.degree);
                this.stop();
            }
            this.direction = dir;
        }
    }

    private void step(Direction dir) {
        boolean obstacle = true;
        Point p = new Point(this.position);
        Point l = new Point(this.location);
        Route.setAsNextPoint(p, dir, this.dependOnSortingZone.getScale());
        Route.setAsNextPoint(l, dir, 1);

        while (obstacle) {
            if (dir == Direction.LEFT || dir == Direction.RIGHT) {
                Direction nextNodeDir = this.dependOnSortingZone.getMap().getRoute(l).getOtherPassableDirection(dir);
                if (nextNodeDir == Direction.UP) {
                    obstacle = this.dependOnSortingZone.getMap().getRobotLayer(l.x, l.y);
                    obstacle |= this.dependOnSortingZone.getMap().getRobotLayer(l.x, l.y - 1);
                } else if (nextNodeDir == Direction.DOWN) {
                    obstacle = this.dependOnSortingZone.getMap().getRobotLayer(l.x, l.y);
                    obstacle |= this.dependOnSortingZone.getMap().getRobotLayer(l.x, l.y + 1);
                }
            } else {
                obstacle = this.dependOnSortingZone.getMap().getRobotLayer(l.x, l.y);
            }
            if (obstacle) {
                this.stop();
                //System.out.println("obstacle");
            }
        }
        // 更新位置
        this.dependOnSortingZone.getMap().setRobotLayer(false, this.location.x, this.location.y);
        this.dependOnSortingZone.getMap().setRobotLayer(true, l.x, l.y);
        this.setLocation(l);

        while (!this.position.equals(p)) {
            //System.out.println(this.position);
            Route.setAsNextPoint(this.position, dir, this.speed);
            this.stop();
        }

        this.power--;
    }

    private void stop() {
        try {
            Thread.sleep(delay);// 50FPS
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }
    }

    private void move() {
        if (this.routes != null) {
            for (Direction dir : this.routes) {
                turn(dir);
                step(dir);
                //System.out.println(String.format("%6s [%d, %d]",dir, this.location.x , this.location.y));
            }
        }
    }

    private void goToPickUpStation() {
        int pickUpStationID = this.pickUpID;
        Point p = this.dependOnSortingZone.getMap().getComponent("pickUpStation", pickUpStationID).getLocation();
        this.initRoutes(this.getLocation(), p);
        this.move();
        this.loadPack(pickUpStationID);
        //System.out.println("--------------loadPack");
    }

    private void goToPutDownStation() {
        int putDownStation = this.pack.getDestination();
        Point p = this.dependOnSortingZone.getMap().getComponent("putDownStation", putDownStation).getLocation();
        this.initRoutes(this.getLocation(), p);
        this.move();
        this.unloadPack();
        //System.out.println("--------------unloadPack");
    }

    private void goToChargingStation() {
        int chargingStation = 301;
        Point p = this.dependOnSortingZone.getMap().getComponent("chargingStation", chargingStation).getLocation();
        this.initRoutes(this.getLocation(), p);
        this.move();
        this.charging();
    }

    private void act() {
        System.out.println(String.format("robot is running!"));
        while (true) {
            try {
                // 检查当前是否持有包裹
                if (this.pack == null) {
                    this.goToPickUpStation();
                } else {
                    this.goToPutDownStation();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        SortingRobot robot = new SortingRobot(new Point(1, 1), zone);
        zone.getMap().print();

        Thread R1 = new Thread(robot);
        R1.start();

    }

}


