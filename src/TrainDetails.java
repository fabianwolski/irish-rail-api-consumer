public class TrainDetails {
    public String origin;
    public String destination;
    public String expectedArrival;
    public String expectedDeparture;
    public String destinationTime;
    public String trainCode;
    public String trainDate;
    public String status;

    public TrainDetails() {}

    @Override
    public String toString() {
        return "Train{" + "origin='" + origin + '\'' + ", destination='" + destination + '\'' + ", expectedArrival='" + expectedArrival + '\'' + ", expectedDeparture='" + expectedDeparture + '\'' + ", destinationTime='" + destinationTime + '\'' + ", trainCode='" + trainCode + '\'' + ", trainDate='" + trainDate + '\'' + ", status='" + status + '\'' + '}';
    }}