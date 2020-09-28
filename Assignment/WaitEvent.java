/**
 * WaitEvent
 */
package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

public class WaitEvent extends Event {
    private final Server server;

    public WaitEvent(Customer customer, List<Server> serverList, Server server) {
        super(customer,serverList);
        this.server = server;
    }
    
    public double compareTime(Server server) {
        return server.getNextAvailableTime();
    }

    @Override
    public Event execute() {
        // Update when is the next time Customer is getting served
        return new ServeEvent(super.getCustomer(),super.getServerList(),this.server,compareTime(this.server));
    }

    @Override
    public Event updateServerList(List<Server> serverList) {
        return new WaitEvent(super.getCustomer(),serverList,this.server);
    }

    @Override
    public String toString() {
        return super.toString() + String.format("waits to be served by %d",this.server.getIdentifier());
    }
    
}
