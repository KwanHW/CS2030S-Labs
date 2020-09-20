// If abstraction is not given, how do you organise the code?
// Will you provide abstarction of serviceTime
public class Cruise {
    private final String identifier;
    private final int arrivalTime;
    private final int numOfLoader;
    private final int serviceTime;

    Cruise(String id, int arrivalTime, int loader, int serviceTime) {
        this.identifier = id;
        this.arrivalTime = arrivalTime; // Time cruise arrives (mins after midnight)
        this.numOfLoader = loader;
        this.serviceTime = serviceTime; // How long it takes to serve the cruise
    }

    public int getServiceCompletionTime() {    
        return getArrivalTime() + serviceTime;
    }

    public int getNumOfLoadersRequired() {
        return numOfLoader;
    }

    // Returns arrival time in minutes
    public int getArrivalTime() {
        int hours = arrivalTime / 100;
        int mins = arrivalTime % 100;
        return hours * 60 + mins; 
    }

    @Override
    public String toString() {
        return String.format("%s@%04d",this.identifier,this.arrivalTime);
    }

}
