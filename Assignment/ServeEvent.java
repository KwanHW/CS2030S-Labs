/**
 * ServeEvent
 */
package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

public class ServeEvent extends Event {
    private final Server server;

    public ServeEvent(Customer customer, List<Server> serverList, Server server, double eventTime) {
        super(customer,serverList,eventTime);
        this.server = server;
    }

    @Override
    public Event execute() {
        // Update the server's information by updating the time 
        Server s = this.server.updateAvailableTime(super.getEventTime() + 1);
        // Set hasWaitingCustomer to false if its true
        if (s.getHasWaitingCustomer()) {
            s = s.toggleWaiting();
        }
        return new DoneEvent(super.getCustomer(),super.updateServer(s),s,s.getNextAvailableTime());
    }

    @Override
    public Event updateServerList(List<Server> serverList) {
        return new ServeEvent(super.getCustomer(),serverList,this.server,super.getEventTime());
    }

    @Override
    public String toString() {
        return super.toString() + String.format("served by %d",this.server.getIdentifier());
    }
    
}
