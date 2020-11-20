/**
 * CustomerQ
 */

package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class CustomerQ {
    private final int queueMax;
    private final List<Double> nextTiming;
    
    private CustomerQ(int queueMax,List<Double> nextTiming) {
        this.queueMax = queueMax;
        this.nextTiming = nextTiming;
    }

    public static CustomerQ make(int queueMax,List<Double> nextTiming) {
        return new CustomerQ(queueMax,nextTiming);
    }

    public boolean contains(double time) {
        return this.nextTiming.contains(time);
    }

    public int queueSize() {
        return this.nextTiming.size();
    }

    public boolean isQueueFull() {
        return this.nextTiming.size() == this.queueMax;
    }
    
    public boolean isQueueEmpty() {
        return this.nextTiming.isEmpty();
    }

    /**
     * Gets the first element of the queue, else return -1.
     * @return The first element of the queue or -1 (if the queue is empty)
    */
    public double getFirst() {
        return this.isQueueEmpty() ? 
            -1 : 
            this.nextTiming.get(0);
    }

    /**
     * Gets the last element of the queue, else return -1.
     * @return The last element of the queue or -1 (if the queue is empty)
    */
    public double getLast() {
        return this.isQueueEmpty() ?
            -1 :
            this.nextTiming.get(this.nextTiming.size() - 1);
    }

    /**
     * Replaces the time with the specified index.
     * @param index The speicified index to be updated
     * @param time The specified time to update
     * @return The updated queue. 
     */
    public CustomerQ replace(int index,double time) {
        ArrayList<Double> newList = new ArrayList<>(this.nextTiming);
        newList.set(index,time);
        return CustomerQ.make(this.queueMax,newList);
    }

    /**
     * Inserts a given time to the end of the queue.
     * @param time The specified time to be passed into the queue.
     * @return The updated queue.
    */
    public CustomerQ put(double time) {
        ArrayList<Double> newList = new ArrayList<>(this.nextTiming);
        newList.add(time);
        return CustomerQ.make(this.queueMax,newList);
    }

    /**
     * Removes the first element of the customer queue and returns the updated 
     * queue. If it is empty, return itself instead. 
     * @return The updated CustomerQ object.
     */
    public CustomerQ pop() {
        if (this.isQueueEmpty()) {
            return this;
        } else {
            List<Double> newList = this.nextTiming.subList(1,this.nextTiming.size());
            return CustomerQ.make(this.queueMax,newList);
        }
    }

    /**
     * Reverses the queue list of next available time.
     * @return The updated CustomerQ object in which the reversed list resides in.
     */
    public CustomerQ reverse() {
        List<Double> newList = new ArrayList<>(this.nextTiming);
        Collections.reverse(newList);
        return CustomerQ.make(this.queueMax,newList);
    }

    /**
     * Sorts the queue list of next available time in ascending order.
     * @return The updated CustomerQ object in which the sorted list resides in.
     */
    public CustomerQ sort() {
        ArrayList<Double> newList = new ArrayList<>(this.nextTiming);
        newList.sort((t1,t2) -> (int)(t1 - t2));
        return CustomerQ.make(this.queueMax,newList);
    }

    @Override
    public String toString() {
        return this.nextTiming.toString();
    }
}
