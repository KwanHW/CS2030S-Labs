public class BigCruise extends Cruise {

    BigCruise(String id,int arrivalTime, double length, double passengers) {
        // Should find a way to calculate the loaders and service time seperately
        super(id,arrivalTime,(int)Math.ceil(length / 40), (int)Math.ceil(passengers / 50));
    }


}
