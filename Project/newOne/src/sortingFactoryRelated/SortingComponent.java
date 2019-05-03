package sortingFactoryRelated;

import java.awt.*;

public class SortingComponent implements Positionable {
    protected int ID;
    protected Point position;
    protected Point location;
    protected int degree; // Clockwise
    protected SortingZone dependOnSortingZone;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public int getDegree() {
        return degree;
    }

    @Override
    public void setDegree(int degree) {
        this.degree = degree;
    }

    public SortingZone getDependOnSortingZone() {
        return dependOnSortingZone;
    }

    public void setDependOnSortingZone(SortingZone dependOnSortingZone) {
        this.dependOnSortingZone = dependOnSortingZone;
    }
}


class ChargingStation extends SortingComponent {
    private int chargingPower;

    public ChargingStation(int chargingPower){
        this.chargingPower = chargingPower;
    }

    public int getChargingPower() {
        return chargingPower;
    }
}

class PutDownStation extends SortingComponent {

}

class PickUpStation extends SortingComponent {

}
