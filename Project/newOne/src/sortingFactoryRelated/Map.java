package sortingFactoryRelated;


import java.awt.*;


public class Map {
    private Route[][] routes;
    private SortingComponent[][] componentLayer;
    private int horizontalSCale;
    private int verticalSCale;
    private int routeHeight;
    private int routeWidth;

    public Map(int horizontalSCale, int verticalSCale, int routeWidth, int routeHeight) {
        boolean up;
        boolean down;
        boolean left;
        boolean right;
        this.routes = new Route[verticalSCale][horizontalSCale];
        this.componentLayer = new SortingComponent[verticalSCale][horizontalSCale];
        this.verticalSCale = verticalSCale;
        this.horizontalSCale = horizontalSCale;
        this.routeHeight = routeHeight;
        this.routeWidth = routeWidth;
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


    public Point getNextPoint(Point point, Direction dir){
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

    public SortingComponent getComponent(Point point) {
        return this.getComponent(point.x, point.y);
    }

    public SortingComponent getComponent(int x, int y) {
        if (0 <= x && x < this.horizontalSCale && 0 <= y && y < this.verticalSCale)
            return this.componentLayer[y][x];
        return null;
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
        Map m = new Map(5, 10, 10, 10);
        m.print();
    }

    public int getRouteHeight() {
        return routeHeight;
    }

    public int getRouteWidth() {
        return routeWidth;
    }
}
