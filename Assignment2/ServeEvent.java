/**
 * ServeEvent
 */

package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

public class ServeEvent extends Event {
    private final Server server;

    /** Constructs a serve event with the specified customer, event time and
     * server.
     * @param customer The customer that is going to be served
     * @param eventTime The time that the customer is being served
     * @param server The server that is going to serve the customer
     */
    public ServeEvent(Customer customer,double eventTime,Server server) {
        super(customer,eventTime,true, x -> {
            // Update the Server's information
            Server s = x.find(y -> y.equals(server)).orElseThrow();
            double time;

            if (eventTime > s.getFirstQueue() || 
                    server instanceof SelfCheck) {
                time = eventTime;
            } else if (s.getFirstQueue() > customer.getArrivalTime()) {
                time = s.getFirstQueue();    
            } else {
                time = customer.getArrivalTime();
            }

            time += customer.getServiceTime();

            Server newServer = s.serve().updateAvailableTime(time);
            Shop newShop = x.replace(newServer);
            return Pair.of(newShop,new DoneEvent(customer,time,newServer));
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
            String.format("served by %s %d",server,this.server.getIdentifier());
    }
    
}
