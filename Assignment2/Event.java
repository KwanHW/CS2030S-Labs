/**
 * Event
 */

package cs2030.simulator;

import java.util.List;
import java.util.function.Function;
import java.util.ArrayList;

public abstract class Event {
    private final Customer customer;
    private final double eventTime;
    private final boolean isValid;
    private final Function<Shop, Pair<Shop,Event>> func; 

    // Convenience function without specified time (use Customer's arrival time)
    public Event(Customer customer,Function<Shop,Pair<Shop,Event>> func) {
        this(customer,customer.getArrivalTime(),true,func);
    }

    // Convenience function with specified validity (useful for DONE & LEAVE)
    // and without specifed time
    public Event(Customer customer,boolean isValid,
            Function<Shop,Pair<Shop,Event>> func) {
        this(customer,customer.getArrivalTime(),isValid,func);
    }

    /**
     * Constructs an Event with the specified customer, eventTime,
     * validity and function.
     * @param customer The specified customer involved in the event.
     * @param eventTime The speicified time of the event.
     * @param isValid The specified validity of the event.
     * @param func A function in which {@link Event#execute(Shop)} will use
    */
    public Event(Customer customer,double eventTime,
            boolean isValid,Function<Shop,Pair<Shop,Event>> func) {
        this.customer = customer;
        //this.rand = rand;
        this.eventTime = eventTime;
        this.isValid = isValid;
        this.func = func;
    }

    public static double compareTimes(double first,double second) {
        return first > second ? first : second;
    }

    public Customer getCustomer() {
        return this.customer;
    }
    
    public double getEventTime() {
        return this.eventTime;
    }

    public boolean isValid() {
        return this.isValid;
    }
    
    public final Pair<Shop,Event> execute(Shop shop) {
        return this.func.apply(shop);
    }

    @Override
    public String toString() {
        String greedy = this.customer.isGreedy() ? "(greedy)" : "";
        return String.format("%.3f %d%s ",this.eventTime,
                this.customer.getId(),
                greedy);
    }

}
