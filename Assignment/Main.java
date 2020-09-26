/**
 * Main
 */
// Import package here
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import cs2030.simulator.Customer;
import cs2030.simulator.Server;
import cs2030.simulator.Event;
import cs2030.simulator.ArriveEvent;
import cs2030.simulator.ServeEvent;
import cs2030.simulator.DoneEvent;
import cs2030.simulator.LeaveEvent;

public class Main {

    public static void main (String[] args) {
        // Initialisation
        Scanner sc = new Scanner(System.in);

        PriorityQueue<Event> simulator = new PriorityQueue<>(new Comparator<Event>(){
            public int compare(Event o1, Event o2) {
                if (o1.getEventTime() == o2.getEventTime()) {
                    if (o1.getCustomer().getId() < o2.getCustomer().getId()) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else if (o1.getEventTime() < o2.getEventTime()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });


        // Initialise Server List
        int numOfServers = sc.nextInt();
        List<Server> serverList = initServers(numOfServers);
        int numOfCustomers = 0;

       int leftCount = 0;
       double totalWait = 0;
           

        // Initialises the Customers
        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            Customer cust = new Customer(++numOfCustomers,arrivalTime);
            // Add them to the PQ
            Event e = new ArriveEvent(cust,serverList);
            simulator.add(e);
        }

        // Loops through the queue
        while (!simulator.isEmpty()){
            Event e = simulator.poll();
            System.out.println(e);
            e = e.updateServerList(serverList);
            if (e instanceof DoneEvent || e instanceof LeaveEvent) {
                e = e.execute();
                serverList = e.getServerList();
                continue;
            } else {
                    e = e.execute();
                    serverList = e.getServerList();
                    simulator.add(e);
            }

            // Statistics
            if (e instanceof LeaveEvent) {
                leftCount++;
            } else if (e instanceof ServeEvent) {
                double timeWaited = e.getEventTime() - e.getCustomer().getArrivalTime();
                totalWait += timeWaited; 
            }
        }

        // Prints the statistics
        int serveCount = numOfCustomers - leftCount;
        double avgWait = totalWait / serveCount;
        String statMsg = String.format("[%.3f %d %d]",avgWait,serveCount,leftCount);
        System.out.println(statMsg);
    }

    public static List<Server> initServers(int numOfServers) {
        ArrayList<Server> serverList = new ArrayList<>(numOfServers);
        for (int i = 0; i < numOfServers; i++) {
            Server server = new Server(i+1,true,false,0);
            serverList.add(server); 
        }
        return serverList;
    }
}
