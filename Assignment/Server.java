/**
 * Server
 */
package cs2030.simulator;

public class Server {
    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;

    public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public boolean getHasWaitingCustomer() {
        return this.hasWaitingCustomer;
    }

    public double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    public Server finishServing() {
        // Server will only become available AT the DoneEvent.execute()
        return new Server(this.identifier,true,false,this.nextAvailableTime);
    }

    // Toggles the isAvailable
    public Server toggleAvailability() {
        return new Server(this.identifier,!this.isAvailable,this.hasWaitingCustomer,this.nextAvailableTime);
    }

    // Toggles the hasWaitingCustomer
    public Server toggleWaiting() {
        return new Server(this.identifier,this.isAvailable,!this.hasWaitingCustomer,this.nextAvailableTime);
    }

    //Updates the nextAvailableTime
    public Server updateAvailableTime(double time) {
        return new Server(this.identifier,this.isAvailable,this.hasWaitingCustomer,time);
    }


    @Override
    public String toString() {
        String message = String.format("%d is ",identifier);
        if (isAvailable) {
            return message + "available";
        } else if (hasWaitingCustomer) {
            return message + String.format("busy; waiting customer to be served at %.3f",nextAvailableTime);
        } else {
            return message + String.format("busy; available at %.3f",nextAvailableTime);
        }
    }

    

    
}
