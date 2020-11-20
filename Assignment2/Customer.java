/**
 * Customer
 */

package cs2030.simulator;

import java.util.function.Supplier;

public class Customer {
    private final int id;
    private final double arrivalTime;
    // Duration of service is lazily called here
    private final Supplier<Double> serviceTime;
    private final boolean isGreedy;

    /**
     * Constructs a Customer with the specified ID and ArrivalTime.
     * @param id The specified ID of the customer
     * @param arrivalTime The specified arrivalTime of the customer 
     */
    public Customer(int id,double arrivalTime) {
        this(id,arrivalTime,false,() -> 1.0);
    }

    /**
     * Constructs a Customer with the specified ID and ArrivalTime.
     * @param id The specified ID of the customer
     * @param arrivalTime The specified arrivalTime of the customer 
     * @param isGreedy Flag to determine if the customer is greedy
     * @param svcTime The specified Supplier that will generate the duration of service time
     */
    public Customer(int id,double arrivalTime,boolean isGreedy,Supplier<Double> svcTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = svcTime;
        this.isGreedy = isGreedy;
    }

    public int getId() {
        return id;
    }

    public boolean isGreedy() {
        return this.isGreedy;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public double getServiceTime() {
        return this.serviceTime.get();
    }

    @Override
    public String toString() {
        return String.format("%d arrives at %.3f",id,arrivalTime);
    }
    
}
