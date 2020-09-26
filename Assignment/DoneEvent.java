/**
 * DoneEvent
 */
package cs2030.simulator;
import java.util.List;
import java.util.Iterator;

public class DoneEvent extends Event {
    private final Server server;

    public DoneEvent(Customer customer,List<Server> serverList,Server server,double eventTime) {
        super(customer,serverList,eventTime);
        this.server = server;
    }
    
    @Override
    public Event execute() {
        // Make Server available again
        Server s = this.server.finishServing();
        return new DoneEvent(super.getCustomer(),super.updateServer(s),s,super.getEventTime());
    } 

    @Override
    public Event updateServerList(List<Server> serverList) {
        return new DoneEvent(super.getCustomer(),serverList,this.server,super.getEventTime());
    }

    @Override
    public String toString() {
        return super.toString() + String.format("done serving by %d",this.server.getIdentifier());
    }
    
}
