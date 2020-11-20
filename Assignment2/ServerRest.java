/**
 * ServerRest
 */

package cs2030.simulator;

public class ServerRest extends Event {
    private final Server server;

    /**
     * Constructs an event to signify that the customer is going to rest.
     * @param customer The specified customer that the server served.
     * @param eventTime The spcecified time which the server is going to rest
     * @param server The specified server that is going to rest.
    */
    public ServerRest(Customer customer,double eventTime,Server server) {
        super(customer,eventTime,true,x -> {
            // Time which server is available to server again
            double back = eventTime + x.getRestDuration();

            Server newServer = x.find(y -> y.equals(server)).orElseThrow();
            newServer = newServer.updateAvailableTime(back);
            Shop newShop = x.replace(newServer);
            return Pair.of(newShop,new ServerBack(customer,back,true,newServer));
        });
        this.server = server;
    }


    @Override
    public String toString() {
        return super.toString() + String.format("%d is resting",this.server.getIdentifier());
    }

}
