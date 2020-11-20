/**
 * DoneEvent
 */

package cs2030.simulator;

import java.util.List;
import java.util.Iterator;

public class DoneEvent extends Event {
    private final Server server;

    public DoneEvent(Customer customer,double eventTime,Server server) {
        this(customer,eventTime,true,server);
    }

    /**
     * Constructs a done event where the server is done serving the customer.
     * @param customer The specified customer that has been served.
     * @param eventTime The specified time where the customer has been served.
     * @param isValid The validity of the event.
     * @param server The specified server that served the customer.
     */
    public DoneEvent(Customer customer,double eventTime,boolean isValid,Server server) {
        super(customer,eventTime,isValid,x -> {
            Server newServer = x.find(y -> y.equals(server)).orElseThrow();
            
            // SelfChecks do not need to rest
            if (!(newServer instanceof SelfCheck)) {
                if (x.goingToRest()) {
                    Shop newShop = x.replace(newServer);
                    return Pair.of(newShop,new ServerRest(customer,eventTime,newServer));
                } 
            }

            newServer = newServer.finishServing();
            Shop newShop = x.replace(newServer);
            return Pair.of(newShop,new DoneEvent(customer,eventTime,false,newServer));
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
            String.format("done serving by %s %d",server,this.server.getIdentifier());
    }
    
}
