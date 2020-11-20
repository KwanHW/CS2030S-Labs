/**
 * SelfCheck
 */

package cs2030.simulator;

import java.util.List;

public class SelfCheck extends Server {
    //private final static CustomerQ queue;

    public SelfCheck(int identifier, boolean isAvailable, boolean hasWaitingCustomer, 
            double nextAvailableTime,CustomerQ queue) {
        super(identifier,isAvailable,hasWaitingCustomer,nextAvailableTime,queue);
    }

    public SelfCheck(int identifier, boolean isAvailable, boolean hasWaitingCustomer, 
            double nextAvailableTime, int queueMax,List<Double> nextTiming) {
        super(identifier,isAvailable,hasWaitingCustomer,nextAvailableTime,
                CustomerQ.make(queueMax,nextTiming));
    }

    public SelfCheck(int identifier, boolean isAvailable, boolean hasWaitingCustomer, 
            double nextAvailableTime,int queueMax) {
        super(identifier,isAvailable,hasWaitingCustomer,nextAvailableTime,
                queueMax,hasWaitingCustomer ? List.of(nextAvailableTime) : List.of());
    }

    @Override
    public SelfCheck finishServing() {
        // SelfCheck will only become available AT the DoneEvent.execute()

        // Get the next availableTime for Customer in queue
        CustomerQ newQ = super.getQueue().pop();

        // As the queue will get smaller when polling for the next isAvailable,
        // the queue will never be full at super point
        return new SelfCheck(super.getIdentifier(),true,false,super.getNextAvailableTime(),newQ);
    }

    // Changes the isAvailable and hasWaitingCustomer flags to
    // reflect that SelfCheck is serving someone
    @Override
    public SelfCheck serve() {
        return new SelfCheck(super.getIdentifier(),false,
                super.hasWaitingCustomer(),super.getNextAvailableTime(),super.getQueue());
    }

    // Inserts the customer into the queue
    @Override
    public SelfCheck sendToWaiting(double time) {
        CustomerQ newQ = super.getQueue().put(time);

        //System.out.println(String.format("INQUEUE: %d/%d",super.nextTiming.size(),this.queueMax));
        boolean isFull = newQ.isQueueFull(); 
        return new SelfCheck(super.getIdentifier(),super.isAvailable(),isFull,time,newQ);
    }

    @Override
    public SelfCheck updateAvailableTime(double time) {
        return new SelfCheck(super.getIdentifier(),super.isAvailable(),
                super.hasWaitingCustomer(),time,super.getQueue());
    }

    public SelfCheck updateQueue(CustomerQ queue) {
        return new SelfCheck(super.getIdentifier(),super.isAvailable(),
                queue.isQueueFull(),super.getNextAvailableTime(),queue);
    }

    @Override
    public String toString() {
        String message = String.format("Self-check %d is ",super.getIdentifier());

        if (super.isAvailable()) {
            return message + "available";
        } else if (super.hasWaitingCustomer()) {
            return message + 
                String.format("busy; waiting customer to be served at %.3f",
                        super.getNextAvailableTime());
        } else {
            return message + String.format("busy; available at %.3f",
                    super.getNextAvailableTime());
        }
    }
}
