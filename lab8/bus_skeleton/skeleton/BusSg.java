import java.util.Scanner;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.concurrent.CompletableFuture;

/**
 * A BusSg class encapsulate the data related to the bus services and
 * bus stops in Singapore, and supports queries to the data.
 *
 * @author: Ooi Wei Tsang
 * @version: CS2030 AY19/20 Semester 1, Lab 10
 */
class BusSg {

    /**
     * Asynchronously returns the set of bus services that serve this bus stop as
     * a set.  Query the web server.
     * @return A promised set of BusService that serve this bus stop.
     */
    public static CompletableFuture<Set<BusService>> getBusServices(BusStop stop) {
        // Passes the API query into scanner
        CompletableFuture<Scanner> sc = BusAPI.getBusServicesAt(stop.getStopId())
            .thenApply(x -> new Scanner(x));

        // Uses the scanned query to form the Set
        CompletableFuture<Set<BusService>> busSvcs = sc.thenApply(scan ->
                scan.useDelimiter("\n")
                .tokens()
                .skip(1) // skip first line
                .flatMap(line -> Stream.of(line.split(",")))
                .map(id -> new BusService(id))
                .collect(Collectors.toSet()));

        sc.thenAccept(Scanner::close);
        return busSvcs;
    }


    /**
     * Given a bus stop and a name, find the bus services that serve between
     * the given stop and any bus stop with matching mame.
     * @param  stop The bus stop.  Assume to be not null.
     * @param  searchString The (partial) name of other bus stops, assume not null.
     * @return The (optional) bus routes between the stops.
     */
    public static CompletableFuture<BusRoutes> findBusServicesBetween(BusStop stop, String searchString) {
        try {
            CompletableFuture<Map<BusService,CompletableFuture<Set<BusStop>>>> validSvcs = BusSg.getBusServices(stop).thenApply(svcs ->
                    svcs.stream().collect(Collectors.toMap(
                            service -> service,
                            service -> service.findStopsWith(searchString))));
            // Inserts the resultant bus stops into bus route
            return validSvcs.thenApply(vs -> new BusRoutes(stop,searchString,vs));
        } catch (CompletionException e) {
            System.err.println("Unable to complete query: " + e);
            return CompletableFuture.completedFuture(new BusRoutes(stop,searchString,Map.of()));
        }

    }

}
