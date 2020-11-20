/**
 * ArriveEvent
 */

package cs2030.simulator;

import java.util.Optional;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.List;

public class ArriveEvent extends Event {

    /**
     * Constructs an arrive event with the specified customer.
     * @param customer The customer that has arrived
     */
    public ArriveEvent(Customer customer) {
        super(customer,x -> {
            // Checks to see if a server is availale
            Optional<Server> server = x.find(y -> y.isAvailable());
            
            // If empty, check to see if server does not have waiting customer
            if (server.isEmpty()) {
                if (customer.isGreedy()) {
                    server = x.greedyFind();
                } else {
                    server = x.find(y -> !y.hasWaitingCustomer());
                }
                if (server.isEmpty()) {
                    // No servers available -> LeaveEvent
                    return Pair.of(x,new LeaveEvent(customer));
                } else {
                    // Toggling happens in WaitEvent
                    Server foundServer = server.orElseThrow();
                    return Pair.of(x,new WaitEvent(customer,foundServer));
                }
            } else {
                // Toggles availability of the server
                // Server will change availability in ServeEvent
                Server foundServer = server.orElseThrow();
                return Pair.of(x,
                        new ServeEvent(customer,customer.getArrivalTime(),foundServer));
            }
        }
        );
    }

    @Override
    public String toString() {
        return super.toString() + "arrives";
    }
    
}
