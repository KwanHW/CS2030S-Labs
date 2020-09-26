/**
 * ArriveEvent
 */
package cs2030.simulator;
import java.util.List;
import java.util.ArrayList;

public class ArriveEvent extends Event {
    public ArriveEvent(Customer customer, List<Server> serverList) {
        super(customer,serverList);
    }

    public double compareTime(Server server) {
        if (super.getCustomer().getArrivalTime() > server.getNextAvailableTime()) {
            return super.getCustomer().getArrivalTime(); 
        } else {
            return server.getNextAvailableTime();
        }
    }

    @Override
    public Event execute() {
        ArrayList<Server> serverList = new ArrayList<>(super.getServerList());
        // Priority -> isAvailable first before hasWaitingCustomer
        // Checks if any Servers that are available
        for (int i = 0; i < serverList.size(); i++) {
            Server s = serverList.get(i);
            // If the server is available, transit to ArriveEvent
            if (s.getIsAvailable()) {
                // Update the availability of the Server
                s = s.toggleAvailability();
                return new ServeEvent(super.getCustomer(),super.updateServer(s),s,compareTime(s));
            }  
        }

        // Checks if the servers have customers that are already waiting
        for (int i = 0; i < serverList.size(); i++) {
            Server s = serverList.get(i);
            // If there isnt any waiting customer, transit to WaitEvent
            if (!s.getHasWaitingCustomer()) {
                // Update the server's availability and set hasWaitingCustomer to false (if any)
                s = s.toggleWaiting();
                return new WaitEvent(super.getCustomer(),super.updateServer(s),s);
            }
        }

        // If there all servers are unavailable and busy
        return new LeaveEvent(super.getCustomer(),this.getServerList());
    }
    
    @Override
    public Event updateServerList(List<Server> serverList) {
        return new ArriveEvent(super.getCustomer(),serverList);
    }
    
    @Override
    public String toString() {
        return super.toString() + "arrives";
    }
    
}
