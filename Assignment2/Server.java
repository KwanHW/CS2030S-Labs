/**
 * Server
 */

package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Server {
    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer; // Flag for checking if queue is FULL
    private final double nextAvailableTime; // For serving the CURRENT Customer
    private final CustomerQ queue;

    /**
     * Constructs a Server.
     * @param identifier The unique identifier of the Server
     * @param isAvailable The availability of the Server
     * @param hasWaitingCustomer If there is a customer waiting to be served
     */
    public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, 
            double nextAvailableTime,CustomerQ queue) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.queue = queue;
    }

    public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, 
            double nextAvailableTime, int queueMax,List<Double> nextTiming) {
        this(identifier,isAvailable,hasWaitingCustomer,nextAvailableTime,
                CustomerQ.make(queueMax,nextTiming));
    }

    public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, 
            double nextAvailableTime,int queueMax) {
        this(identifier,isAvailable,hasWaitingCustomer,nextAvailableTime,
                queueMax,hasWaitingCustomer ? List.of(nextAvailableTime) : List.of());
    }

    // Constructor for backward compatibility
    public Server(int identifier,boolean isAvailable,boolean hasWaitingCustomer,
            double nextAvailableTime) {
        this(identifier,isAvailable,hasWaitingCustomer,nextAvailableTime,1);
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public boolean hasWaitingCustomer() {
        return this.hasWaitingCustomer;
    }

    public CustomerQ getQueue() {
        return this.queue;
    }

    public int queueSize() {
        return this.queue.queueSize();
    }

    public boolean isQueueEmpty() {
        return this.queue.isQueueEmpty();
    }

    public boolean isQueueFull() {
        return this.queue.isQueueFull();
    }

    /**
     * Gets the first timing in the {@link CustomerQ}. If the queue is empty,
     * return the next available time instead.
     * @return The first timing of the queue or the next available time of the
     *      server (if the queue is empty).
     */
    public double getFirstQueue() {
        return this.isQueueEmpty() ? 
            this.nextAvailableTime : 
            this.queue.getFirst();
    }

    public double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    // JavaDoc
    public boolean hasDuplicateTime(double time) {
        return this.queue.contains(time);
    }

    /** 
     * Changes the server information to reflect that it is done serving
     * the customer.
     * @return The updated Server instance
     */
    public Server finishServing() {
        // Server will only become available AT the DoneEvent.execute()

        // Get the next availableTime for Customer in queue
        double newAvailable = this.queue.getFirst() == -1 ? 
            this.nextAvailableTime : this.queue.getFirst();
        CustomerQ newQ = this.queue.pop();

        // As the queue will get smaller when polling for the next isAvailable,
        // the queue will never be full at this point
        return new Server(this.identifier,true,false,newAvailable,newQ);
    }

    // Changes the isAvailable and hasWaitingCustomer flags to
    // reflect that Server is serving someone
    public Server serve() {
        return new Server(this.identifier,false,this.hasWaitingCustomer,
                this.nextAvailableTime,this.queue);
    }

    /**Inserts a new waiting time into {@link CustomerQ} and updates the 
     * server.
     * @param time The specified time to insert into the {@link CustomerQ}.
     * @return A server with the updated {@link CustomerQ}.
     */
    public Server sendToWaiting(double time) {
        CustomerQ newQ = this.queue.put(time);

        //System.out.println(String.format("INQUEUE: %d/%d",this.nextTiming.size(),this.queueMax));
        boolean isFull = newQ.isQueueFull(); 
        return new Server(this.identifier,this.isAvailable,isFull,time,newQ);
    }

    /** 
     * Updates the nextAvailableTime specified by adding the current time and
     * the specified time.
     * @param time The new time that the Server will be available
     * @return The updated Server instance
     */
    public Server updateAvailableTime(double time) {
        if (this.isQueueEmpty()) {
            return new Server(this.identifier,this.isAvailable,
                    this.hasWaitingCustomer,time,this.queue);
        } 
        CustomerQ newQ = this.queue.replace(0,time).sort();
        return new Server(this.identifier,this.isAvailable,this.hasWaitingCustomer,time,newQ);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Server) {
            Server serverObj = (Server)obj;
            return serverObj.getIdentifier() == this.identifier;
        }

        return false;
    }

    @Override
    public String toString() {
        String message = String.format("%d is ",this.identifier);

        //String inQ = String.format("INQUEUE: %d/%d",this.nextTiming.size(),this.queueMax);
        //message = inQ+message;
        if (isAvailable) {
            return message + "available";
        } else if (hasWaitingCustomer) {
            return message + 
                String.format("busy; waiting customer to be served at %.3f",nextAvailableTime);
        } else {
            return message + String.format("busy; available at %.3f",nextAvailableTime);
        }
    }
}

