package sortingFactoryRelated;

public class Pack {
    private Integer originationID;
    private Integer destinationID;


    public Pack(Integer originationID, Integer destinationID) {
        this.originationID = originationID;
        this.destinationID = destinationID;
    }

    public Integer getOrigination() {
        return originationID;
    }

    public Integer getDestination() {
        return destinationID;
    }


}
