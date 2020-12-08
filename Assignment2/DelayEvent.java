/**
 * DelayEvent
 */

package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class DelayEvent extends Event {
    private final Server server;

    /**
     * Constructs a delay event with the specified customer, event time
     * and the Server associated.
     * @param customer The specified customer that is waiting to be served.
     * @param eventTime The time of the event.
     * @param server The specified server that the customer is waiting on.
     */
    public DelayEvent(Customer customer,double eventTime,Server server) {
        super(customer,eventTime,true,x -> {
            Server newServer = x.find(y -> y.equals(server)).orElseThrow();
            double time = newServer.getNextAvailableTime(); 

            if (time > newServer.getFirstQueue()) {
                newServer = newServer.updateAvailableTime(time);
            }

            // Find an available SelfCheck
            if (newServer instanceof SelfCheck) {
                ArrayList<Server> selfChecks = new ArrayList<>(x.findSelfChecks());
                Collections.sort(
                        selfChecks,
                    (s1,s2) -> 
                        s1.getNextAvailableTime() < s2.getNextAvailableTime() ?
                        -1 : 1
                );
                newServer = selfChecks.get(0);
                time = newServer.getNextAvailableTime();
            }

            Shop newShop = x.replace(newServer);
            // If not available, Delay it
            if (!newServer.isAvailable()) {
                return Pair.of(newShop,new DelayEvent(
                            customer,
                            time,
                            newServer));
            } 

            // If available, Serve it
            return Pair.of(newShop,new ServeEvent(
                        customer,
                        time,
                        newServer
                        ));
        });
        this.server = server;
    }

    @Override
    public String toString() {
        return super.toString() + 
            String.format("waits to be served by server %d",this.server.getIdentifier());
    }

}
