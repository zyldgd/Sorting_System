package sortingFactoryRelated;


import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;


public class Map {
    private int horizontalSCale;
    private int verticalSCale;
    private Route[][] routes;
    private boolean[][] robotLayer;
    private HashMap<Integer, SortingComponent> pickUpStations;
    private HashMap<Integer, SortingComponent> putDownStations;
    private HashMap<Integer, SortingComponent> chargingStations;

    public int getHorizontalSCale(){
        return this.horizontalSCale;
    }

    public int getVerticalSCale(){
        return this.verticalSCale;
    }

    public Map(int horizontalSCale, int verticalSCale) {
        boolean up;
        boolean down;
        boolean left;
        boolean right;
        this.verticalSCale = verticalSCale;
        this.horizontalSCale = horizontalSCale;
        this.routes = new Route[verticalSCale][horizontalSCale];
        this.robotLayer = new boolean[verticalSCale][horizontalSCale];
        this.pickUpStations = new HashMap<>();
        this.putDownStations = new HashMap<>();
        this.chargingStations = new HashMap<>();

        for (int h = 0; h < verticalSCale; h++) {
            if (h % 2 == 0) {
                left = false;
                right = true;
            } else {
                left = true;
                right = false;
            }


            for (int w = 0; w < horizontalSCale; w++) {
                if (w % 2 == 0) {
                    up = false;
                    down = true;
                } else {
                    up = true;
                    down = false;
                }
                Route route = new Route(up, down, left, right);
                this.setRoute(route, w, h);
            }
        }
        for (int h = 0; h < verticalSCale; h++) {
            for (int w = 0; w < horizontalSCale; w++) {
                if (h == (verticalSCale - 1)) {
                    this.getRoute(w, h).setUpOut(false);
                }
                if (w == (horizontalSCale - 1)) {
                    this.getRoute(w, h).setRightOut(false);
                }
                if (h == 0) {
                    this.getRoute(w, h).setDownOut(false);
                }
                if (w == 0) {
                    this.getRoute(w, h).setLeftOut(false);
                }
            }
        }

    }

    public boolean isPassable(Point point, Direction dir) {
        if (this.getRoute(point) != null) {
            return this.getRoute(point).passable(dir);
        }
        return false;
    }

    public void setRobotLayer(boolean isRobot, int x, int y) {
        if (0 <= x && x < this.horizontalSCale && 0 <= y && y < this.verticalSCale)
            this.robotLayer[y][x] = isRobot;
    }

    public boolean getRobotLayer(int x, int y) {
        if (0 <= x && x < this.horizontalSCale && 0 <= y && y < this.verticalSCale)
            return this.robotLayer[y][x];
        return false;
    }

    public void setRoute(Route route, int x, int y) {
        this.routes[y][x] = route;
    }

    public Route getRoute(int x, int y) {
        if (0 <= x && x < this.horizontalSCale && 0 <= y && y < this.verticalSCale)
            return this.routes[y][x];
        return null;
    }

    public Route getRoute(Point point) {
        return this.getRoute(point.x, point.y);
    }

    public Point getNextPoint(Point point, Direction dir) {
        switch (dir) {
            case UP:
                return new Point(point.x, point.y + 1);
            case DOWN:
                return new Point(point.x, point.y - 1);
            case LEFT:
                return new Point(point.x - 1, point.y);
            case RIGHT:
                return new Point(point.x + 1, point.y);
        }
        return null;
    }

    public void addComponent(String name, Integer componentID, SortingComponent sortingComponent) {
        switch (name) {
            case "putDownStation":
                this.putDownStations.put(componentID, sortingComponent);
            case "pickUpStation":
                this.pickUpStations.put(componentID, sortingComponent);
            case "chargingStation":
                this.chargingStations.put(componentID, sortingComponent);
        }
    }

    /**
     * @param name {"putDownStation","pickUpStation","chargingStation"}
     * @return HashMap
     */
    public HashMap<Integer, SortingComponent> getComponents(String name) {
        switch (name) {
            case "putDownStation":
                return this.putDownStations;
            case "pickUpStation":
                return this.pickUpStations;
            case "chargingStation":
                return this.chargingStations;
        }
        return null;
    }

    /**
     * @param name        {"putDownStation","pickUpStation","chargingStation"}
     * @param componentID ID of the SortingComponent
     * @return SortingComponent
     * @see PutDownStation
     * @see PickUpStation
     * @see ChargingStation
     */
    public SortingComponent getComponent(String name, Integer componentID) {
        switch (name) {
            case "putDownStation":
                return this.putDownStations.get(componentID);
            case "pickUpStation":
                return this.pickUpStations.get(componentID);
            case "chargingStation":
                return this.chargingStations.get(componentID);
        }
        return null;
    }

    public static int getDistance(Point p1, Point p2, Direction dir) {
        if (dir.equals(Direction.UP) || dir.equals(Direction.DOWN))
            return Math.abs(p1.y - p2.y);
        else
            return Math.abs(p1.x - p2.x);
    }

    public void print() {
        String s;
        for (int y = this.verticalSCale - 1; y >= 0; y--) {
            for (int x = 0; x < this.horizontalSCale; x++) {
                s = "";
                if (this.getRoute(x, y).isUpOut()) {
                    s += "↑";
                }
                if (this.getRoute(x, y).isDownOut()) {
                    s += "↓";
                }
                if (this.getRoute(x, y).isLeftOut()) {
                    s += "←";
                }
                if (this.getRoute(x, y).isRightOut()) {
                    s += "→";
                }

                System.out.printf(" %4s ", s);
            }
            System.out.printf("\n");
        }
    }

    public static void main(String[] args) {
        Map m = new Map(6, 10);
        m.print();
    }

}
