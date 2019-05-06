package sortingFactoryRelated;

import java.awt.*;
import java.util.Random;

public class SortingComponent implements Positionable {
    protected Point position;
    protected Point location;
    protected int degree; // Clockwise
    protected SortingZone dependOnSortingZone;

    public SortingComponent(Point location, SortingZone dependOnSortingZone){
        this.location = location;
        this.dependOnSortingZone = dependOnSortingZone;
        int scale = this.dependOnSortingZone.getScale();
        this.position = new Point(this.location.x*scale,this.location.y*scale);
        this.degree = 0;
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

    public ChargingStation(int chargingPower,Point location, SortingZone dependOnSortingZone) {
        super(location, dependOnSortingZone);
        this.chargingPower = chargingPower;
    }

    public int getChargingPower() {
        return chargingPower;
    }
}

class PutDownStation extends SortingComponent {

    public PutDownStation(Point location, SortingZone dependOnSortingZone) {
        super(location, dependOnSortingZone);
    }
}

class PickUpStation extends SortingComponent {
    private Pack pack;
    private Random random;
    private Integer[] putDownStationKeys;

    public PickUpStation(Point location, SortingZone dependOnSortingZone) {
        super(location, dependOnSortingZone);
        this.random = new Random();
        this.putDownStationKeys = this.dependOnSortingZone.getMap().getComponents("putDownStation").keySet().toArray(new Integer[0]);
        this.setPack();
    }

    private void setPack() {
        Integer pickUpStationID = 0;
        Integer putDownStationID = putDownStationKeys[this.random.nextInt(putDownStationKeys.length)];
        this.pack = new Pack(pickUpStationID, putDownStationID);

    }

    public Pack getPack() {
        Pack packTemp = this.pack;
        this.setPack();
        return packTemp;
    }


}
