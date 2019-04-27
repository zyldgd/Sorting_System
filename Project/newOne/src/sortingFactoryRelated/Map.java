package sortingFactoryRelated;

import java.awt.*;


public class Map {
    private Route[][] Routes;
    private int height;
    private int width;

    public Map(int width, int height) {
        boolean up;
        boolean down;
        boolean left;
        boolean right;
        this.Routes = new Route[height][width];
        this.height = height;
        this.width = width;
        for (int h = 0; h < height; h++) {
            if (h % 2 == 0) {
                left = false;
                right = true;
            } else {
                left = true;
                right = false;
            }
            for (int w = 0; w < width; w++) {
                if (w % 2 == 0) {
                    up = false;
                    down = true;
                } else {
                    up = true;
                    down = false;
                }
                Route route = new Route( up, down, left, right);
                this.setRoute(route, w, h);
            }
        }
    }

    public Route getRoute(int x, int y) {
        if (0 <= x && x < this.width && 0 <= y && y < this.height)
            return this.Routes[y][x];
        return null;
    }

    public void setRoute(Route route, int x, int y) {
        this.Routes[y][x] = route;
    }

    public Route getNextRoute(int curX, int curY, Direction dir) {
        switch (dir) {
            case UP:
                return this.getRoute(curX, curY + 1);
            case DOWN:
                return this.getRoute(curX, curY - 1);
            case LEFT:
                return this.getRoute(curX + 1, curY);
            case RIGHT:
                return this.getRoute(curX - 1, curY);
        }
        return null;
    }

    public void print() {
        String s;
        for (int y = this.height - 1; y >= 0; y--) {
            for (int x = 0; x < this.width; x++) {
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
        Map m = new Map(5, 10);
        m.print();
    }
}
