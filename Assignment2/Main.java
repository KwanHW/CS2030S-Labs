/**
 * Main
 */

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import cs2030.simulator.Customer;
import cs2030.simulator.Server;
import cs2030.simulator.Event;
import cs2030.simulator.ArriveEvent;
import cs2030.simulator.WaitEvent;
import cs2030.simulator.ServeEvent;
import cs2030.simulator.DelayEvent;
import cs2030.simulator.DoneEvent;
import cs2030.simulator.LeaveEvent;
import cs2030.simulator.ServerRest;
import cs2030.simulator.ServerBack;
import cs2030.simulator.Pair;
import cs2030.simulator.Shop;
import cs2030.simulator.RandomGenerator;
import cs2030.simulator.RandomGen;
import cs2030.simulator.Rest;

public class Main {

    /**
     * Initalises the simulator and runs the simulation through a {@link PriorityQueue}.
     * Once completed, the simulation will print out 3 numbers (in order):
     * 1. Average Waiting Time
     * 2. Number of customers served
     * 3. Number of customers left
     */
    public static void main(String[] args) {
        // Initialisation
        ArrayList<String> argsList = new ArrayList<>(List.of(args));
        // Settings (in order of representation)
        int seed = Integer.valueOf(argsList.get(0));
        int servers = Integer.valueOf(argsList.get(1));
        int selfCheckout = 0;
        int queueLen = 1;
        int customers = 0;
        double lambda = 0;
        double mu = 0;
        double rho = 0;
        double restProb = 0;
        double greedyProb = argsList.size() == 10 ? 
            Double.valueOf(argsList.get(9)) : 0.0;

        // Depending on the variables given the settings will change
        switch (argsList.size()) {
            // Normal simulator
            case 5:
                customers = Integer.valueOf(argsList.get(2));
                lambda = Double.valueOf(argsList.get(3));
                mu = Double.valueOf(argsList.get(4));
                break;
            // Queues included in simulator
            case 6:
                queueLen = Integer.valueOf(argsList.get(2));
                customers = Integer.valueOf(argsList.get(3));
                lambda = Double.valueOf(argsList.get(4));
                mu = Double.valueOf(argsList.get(5));
                break;
            // Server rest states included
            case 8:
                queueLen = Integer.valueOf(argsList.get(2));
                customers = Integer.valueOf(argsList.get(3));
                lambda = Double.valueOf(argsList.get(4));
                mu = Double.valueOf(argsList.get(5));
                rho = Double.valueOf(argsList.get(6));
                restProb = Double.valueOf(argsList.get(7)); 
                break;
            // Self checkout kiosks included
            case 9:
            case 10:
                selfCheckout = Integer.valueOf(argsList.get(2));
                queueLen = Integer.valueOf(argsList.get(3));
                customers = Integer.valueOf(argsList.get(4));
                lambda = Double.valueOf(argsList.get(5));
                mu = Double.valueOf(argsList.get(6));
                rho = Double.valueOf(argsList.get(7));
                restProb = Double.valueOf(argsList.get(8)); 
                break;
            default:
                break;
        }

        RandomGen rand = new RandomGen(seed,lambda,mu,rho);

        PriorityQueue<Event> simulator = new PriorityQueue<>(new Comparator<Event>() {
            public int compare(Event o1, Event o2) {
                if (o1.getEventTime() == o2.getEventTime()) {
                    if (o1 instanceof DelayEvent && o2 instanceof DelayEvent) {
                        return o1.getCustomer().getId() - o2.getCustomer().getId();
                    } else if (o1 instanceof DelayEvent) {
                        return 1;
                    } else if (o2 instanceof DelayEvent) {
                        return -1;
                    } else {
                        return o1.getCustomer().getId() - o2.getCustomer().getId();
                    }
                } else {
                    return o1.getEventTime() < o2.getEventTime() ? -1 : 1;
                }
            }
        });


        // Initialise Server List
        Rest rest = new Rest(() -> rand.getRandomRest(),
            () -> rand.getRestPeriod(),
            restProb);
        Shop shop = new Shop(servers,selfCheckout,queueLen,rest);
        initCustomers(simulator,rand,customers,greedyProb);

        // To track statistics
        int leftCount = 0;
        double totalWait = 0;

        // Loops through the queue
        while (!simulator.isEmpty()) {
            Event e = simulator.poll();

            if (!(e instanceof DelayEvent || 
                        e instanceof ServerRest || 
                        e instanceof ServerBack)) {
                System.out.println(e);
            }

            Pair<Shop,Event> pair = e.execute(shop);

            // Updates the shop
            shop = pair.first();
            e = pair.second();

            // If the Event is no longer valid, do not put it back in the PQ
            if (e.isValid()) {
                simulator.add(e);
            } 

            // Statistics
            if (e instanceof LeaveEvent && e.isValid()) {
                leftCount++;
            } else if (e instanceof ServeEvent) {
                double timeWaited = e.getEventTime() - e.getCustomer().getArrivalTime();
                totalWait += timeWaited; 
            }
        }

        // Prints the statistics
        int serveCount = customers - leftCount;
        // Prevent NaN Errors
        double avgWait = serveCount == 0 ? 0 : totalWait / serveCount;
        String statMsg = String.format("[%.3f %d %d]",avgWait,serveCount,leftCount);
        System.out.println(statMsg);
    }

    /**
     * Initialises the {@link Customer} for the simulator using the referenced
     * simulator, random generator, customers and greedy probability.
     * @param pq The specified {@link PriorityQueue} in which the simulator resides in.
     * @param rand The specified {@link RandomGen} to generate the respective random values.
     * @param customers The specified number of customers to generate.
     * @param greedyProb The specified probability for a customer to be greedy.
     */
    public static void initCustomers(PriorityQueue<Event> pq,
            RandomGen rand,int customers,double greedyProb) {
        double arriveTime = 0;
        // Initialises the Customers
        for (int i = 1; i <= customers; i++) {
            Customer cust;
            boolean isGreedy = false;
            if (greedyProb != 0) {
                isGreedy = rand.getCustomerType() < greedyProb;
            }

            cust = new Customer(i,arriveTime,isGreedy,() -> rand.getService());
            double arr = rand.getArrival();
            arriveTime += arr;

            // Add customer to the PQ
            Event e = new ArriveEvent(cust);
            pq.add(e);
        }
    }
}
