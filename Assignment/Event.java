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

    public Event(Customer customer,List<Server> serverList) {
        this.customer = customer;
        this.serverList = serverList;
        this.eventTime = setEventTime();
    }

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

    public double setEventTime() {
        return this.customer.getArrivalTime();
    }

    // Updates the server and returns an update Immutable List
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


    // Handles the transition of one event to another
    public abstract Event execute(); 

    public abstract Event updateServerList(List<Server> serverList);

    @Override
    public String toString() {
        return String.format("%.3f %d ",this.eventTime,this.customer.getId());
    }

}
