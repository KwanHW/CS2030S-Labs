/**
 * LeaveEvent
 */

package cs2030.simulator;

import java.util.List;
import java.util.Iterator;

public class LeaveEvent extends Event {
    public LeaveEvent(Customer customer,boolean isValid) {
        super(customer,isValid,x -> Pair.of(x,new LeaveEvent(customer,false)));
    }

    public LeaveEvent(Customer customer) {
        this(customer,true);
    }
    
    @Override
    public String toString() {
        return super.toString() + "leaves";
    }
    
}
