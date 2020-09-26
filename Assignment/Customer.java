/**
 * Customer
 */
package cs2030.simulator;
public class Customer {
    private final int id;
    private final double arrivalTime;

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
