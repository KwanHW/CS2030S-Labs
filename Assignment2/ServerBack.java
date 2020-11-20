/**
 * ServerBack
 */

package cs2030.simulator;

public class ServerBack extends Event {
    private final Server server;

    /**
     * Constructs an event which signifies that the {@link Server} is back from
     * rest.
     * @param customer The specified customer that the server served before resting.
     * @param isValid The validity of the server.
     * @param eventTime The specified time where the server will be done resting.
     * @param server The specified server that is coming back from rest.
     */
    public ServerBack(Customer customer,double eventTime,boolean isValid,Server server) {
        super(customer,eventTime,isValid,x -> {
            Server newServer = x.find(y -> y.equals(server)).orElseThrow();
            newServer = newServer.finishServing();

            Shop newShop = x.replace(newServer);
            return Pair.of(newShop,new ServerBack(customer,eventTime,false,newServer));
        });
        this.server = server;
    }


    @Override
    public String toString() {
        return super.toString() + 
            String.format("%d is back from rest",this.server.getIdentifier());
    }
    
}
