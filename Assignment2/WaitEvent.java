/**
 * WaitEvent
 */

package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Collections;

public class WaitEvent extends Event {
    private final Server server;

    /** Constructs a wait event where the customer waits to be served.
     * @param customer The specified customer that is waiting to be served
     * @param server The specified server that will be serving the customer
    */
    public WaitEvent(Customer customer,Server server) {
        super(customer,x -> {
            Server newServer = x.find(y -> y.equals(server)).orElseThrow();
            double time = newServer.getNextAvailableTime(); 

            // A customer can get served by a different SelfCheck
            if (newServer instanceof SelfCheck) {
                // Bad var name :(
                ArrayList<Server> newList = new ArrayList<>(x.findSelfChecks());
                Collections.sort(
                        newList,
                    (s1,s2) -> 
                        (s1.getNextAvailableTime() < s2.getNextAvailableTime()) ?
                        -1 : 1
                );
                newServer = newList.get(0);
                time = newServer.getNextAvailableTime();
            }

            newServer = newServer.sendToWaiting(time);
            Shop newShop = x.replace(newServer);
            
            // Prevent 2 customers from being served at the same time
            if (newServer.hasDuplicateTime(time)) {
                //System.out.println("Enter Delay: "+newServer.getQueue());
                return Pair.of(newShop,new DelayEvent(
                            customer,
                            time,//+0.0001,
                            newServer));
            } 

            // No one in the queue, can go to Serve (Delay Event incase server is Resting)
            return Pair.of(newShop,new DelayEvent(
                        customer,
                        time,
                        newServer
                        ));
        });
        this.server = server;
    }

    @Override
    public String toString() {
        String server = "server";
        if (this.server instanceof SelfCheck) {
            server = "self-check";
        }

        return super.toString() + 
            String.format("waits to be served by %s %d",server,this.server.getIdentifier());
    }
}
