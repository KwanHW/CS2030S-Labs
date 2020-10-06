/**
 * LeaveEvent
 */

package cs2030.simulator;

import java.util.List;
import java.util.Iterator;

public class LeaveEvent extends Event {
    public LeaveEvent(Customer customer,List<Server> serverList) {
        super(customer,serverList);
    }
    
    @Override
    public Event execute() {
        return this;
    }

    @Override
    public Event updateServerList(List<Server> serverList) {
        return new LeaveEvent(super.getCustomer(),serverList);
    }

    @Override
    public String toString() {
        return super.toString() + "leaves";
    }
    
}
