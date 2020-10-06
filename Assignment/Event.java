/**
 * Event
 */

package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

public abstract class Event {
    private final Customer customer;
    private final double eventTime;
    private final List<Server> serverList; 

    /**
     * Constructs an Event with a Customer and a server list. The event time is
     * determined by the Customer's arrivalTime (unless specified).
     * @param customer The customer being served for the event
     * @param serverList A list of servers that may serve the customer
    */
    public Event(Customer customer,List<Server> serverList) {
        this.customer = customer;
        this.serverList = serverList;
        this.eventTime = customer.getArrivalTime();
    }

    /**
     * Constructs an Event with a specified event time.
     * @param customer The customer being served for the event
     * @param serverList A lis of servers that may serve the customer
     * @param eventTime The specified event time set
    */
    public Event(Customer customer,List<Server> serverList,double eventTime) {
        this.customer = customer;
        this.serverList = serverList;
        this.eventTime = eventTime;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    public List<Server> getServerList() {
        return serverList;
    }

    public double getEventTime() {
        return this.eventTime;
    }
    
    /*
    public double setEventTime() {
        return this.customer.getArrivalTime();
    }
    */

    /**
     * Takes in a Server and update the serverList using the Server's ID.
     * @param s The server that will be updated
     * @return An immutable list containing the updated server
    */
    public List<Server> updateServer(Server s) {
        ArrayList<Server> serverList = new ArrayList<Server>(this.serverList);
        int id = s.getIdentifier();
        for (int i = 0; i < serverList.size(); i++) {
            if (id == serverList.get(i).getIdentifier()) {
                serverList.set(i,s);
                return serverList;
            }
        }
        return this.serverList;
    }

    /**
     * Executes the specified actions in the Event
     * @return The output after the Event's action is executed
    */
    public abstract Event execute(); 

    /**
     * Updates the serverList in the event for execution
     * @param serverList The updated serverList specified
     * @return The updated event for execution
     */
    public abstract Event updateServerList(List<Server> serverList);

    @Override
    public String toString() {
        return String.format("%.3f %d ",this.eventTime,this.customer.getId());
    }

}
