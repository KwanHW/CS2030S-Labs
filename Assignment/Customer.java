/**
 * Customer
 */

package cs2030.simulator;

public class Customer {
    private final int id;
    private final double arrivalTime;

    /**
     * Constructs a Customer with the specified ID and ArrivalTime
     * @param id The specified ID of the customer
     * @param arrivalTime The specified arrivalTime of the customer 
     */
    public Customer(int id,double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    public int getId() {
        return id;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public String toString() {
        return String.format("%d arrives at %.1f",id,arrivalTime);
    }
    
}
