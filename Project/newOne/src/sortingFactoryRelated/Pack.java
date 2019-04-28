package sortingFactoryRelated;

public class Pack {
    private int ID;
    private int origination;
    private int destination;


    public Pack(int ID, int origination,int destination){
        this.ID = ID;
        this.origination = origination;
        this.destination = destination;
    }

    public int getID() {
        return ID;
    }

    public int getOrigination() {
        return origination;
    }

    public int getDestination() {
        return destination;
    }


}
