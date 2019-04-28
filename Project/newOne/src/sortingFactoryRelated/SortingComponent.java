package sortingFactoryRelated;

import java.awt.*;

public class SortingComponent implements Positionable{
    public int ID;
    public Point position;
    public Point location;
    public int degree = 0; // Clockwise
    protected SortingZone dependOnSortingZone;


    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public Boolean setPosition(Point position) {
        this.position = position;
        return null;
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public Boolean setLocation(Point location) {
        return null;
    }


    public SortingZone getDependOnSortingZone() {
        return dependOnSortingZone;
    }

    public void setDependOnSortingZone(SortingZone dependOnSortingZone) {
        this.dependOnSortingZone = dependOnSortingZone;
    }
}


class ChargingStation extends SortingComponent {

}

class PutDownStation extends SortingComponent {

}

class PickUpStation extends SortingComponent {

}
