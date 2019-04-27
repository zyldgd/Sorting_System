package sortingFactoryRelated;

import java.awt.*;
import java.util.ArrayList;

public class SortingZone implements Positionable {
    public ArrayList<Route> routeList;
    public Point position = new Point(0,0);

    public SortingZone(int routesCount){
        this.routeList = new ArrayList<Route>(routesCount);
    }

    public SortingZone(ArrayList<Route> routes){
        this.routeList = routes;
    }


    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public Boolean setPosition(Point p) {
        this.position = p;
        return true;
    }
}
