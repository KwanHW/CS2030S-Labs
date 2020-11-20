package cs2030.simulator;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ArrayList;

public class Shop {
    private final List<Server> serverList;
    private final Rest rest;

    /**
     * Constructs a shop with the specified number of servers (normal and self-checkout),
     * maximum queue length and the rest object.
     * @param numServer The specified number of normal servers
     * @param numSelfCheck The specified number of self-checkout servers
     * @param queueLen The specified queue length for all servers
     * @param rest The specified rest object
    */
    public Shop(int numServer,int numSelfCheck,int queueLen,Rest rest) {
        Stream<Server> servers = IntStream.rangeClosed(1,numServer)
            .boxed()
            .map(x -> new Server(x,true,false,0,queueLen));

        Stream<SelfCheck> selfChecks = Stream.empty();
        if (numSelfCheck > 0) {
            selfChecks = IntStream
                .rangeClosed(numServer + 1,numServer + numSelfCheck)
                .boxed()
                .map(x -> new SelfCheck(x,true,false,0,queueLen));
        }
        this.serverList = Stream.concat(servers,selfChecks).collect(Collectors.toList());
        this.rest = rest;
    }

    // Backward Compatibility Constructors
    public Shop(List<Server> servers,Rest rest) {
        this.serverList = servers;
        this.rest = rest;
    }

    public Shop(List<Server> servers) {
        this.serverList = servers;
        this.rest = Rest.empty();
    }

    public Shop(int numServer) {
        this(numServer,0,1,Rest.empty());
    }

    /**
     * Retrieves all {@link SelfCheck} instances that exist in the Shop.
     * @return List containing all {@link SelfCheck} instances 
     */
    public List<Server> findSelfChecks() {
        return this.serverList.stream()
            .filter(x -> x instanceof SelfCheck)
            .collect(Collectors.toList());
    }

    /**
     * Updates all the customer queues with the specified server (SelfCheck) by using its
     * {@link CustomerQ}.
     * @param s The specified SelfCheck to update.
     * @return The updated Shop that contains the updated queues of the {@link SelfCheck}
     */
    public Shop updateSelfCheck(Server s) {
        CustomerQ queue = s.getQueue();
        List<Server> newList = this.serverList.stream()
            .map(x -> x.equals(s) ? s : x)
            .map(x -> x instanceof SelfCheck ? ((SelfCheck)x).updateQueue(queue) : x)
            .collect(Collectors.toList());
        return new Shop(newList,this.rest);
    }

    public boolean goingToRest() {
        return rest.goingToRest();
    }

    public double getRestDuration() {
        return rest.getRestDuration();
    }

    public Optional<Server> find(Predicate<Server> pred) {
        return this.serverList.stream().filter(pred).findFirst();
    }

    /**
     * Greedily searches for a Server that has the shortest queue. If all
     * servers queues are full, return an empty Optional.
     * @return An Optional describing the server with the shortest queue, or an
     * empty optional if all servers queues are full.
     */
    public Optional<Server> greedyFind() {
        if (this.serverList.size() == 1) {
            return Optional.of(serverList.get(0));
        } else {
            return this.serverList.stream()
                // Finds all servers who are not full
                .filter(x -> !x.isQueueFull())
                // Gets the smallest server size
                .min((s1,s2) -> s1.queueSize() - s2.queueSize());
        }
    }

    /**
     * Updates a specified server. If the server is a {@link SelfCheck}, update
     * the queues of the other {@link SelfChek} in the Shop too.
     * @param s The specified server to be updated
     * @return The updated shop with the specified server in it. 
     */
    public Shop replace(Server s) {
        if (s instanceof SelfCheck) {
            return this.updateSelfCheck(s);
        }

        List<Server> newList = List.copyOf(this.serverList);
        newList = newList.stream()
            .map(x -> x.equals(s) ? s : x)
            .collect(Collectors.toList());
        return new Shop(newList,this.rest);
    }

    @Override
    public String toString() {
        return serverList.toString();
    }
}
