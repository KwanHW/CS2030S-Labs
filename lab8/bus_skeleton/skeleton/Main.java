import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/**
 * This program finds different ways one can travel by bus (with a bit 
 * of walking) from one bus stop to another.
 *
 * @author: Ooi Wei Tsang
 * @version: CS2030 AY19/20 Semester 1, Lab 10
 */
public class Main {
  /**
   * The program read a sequence of (id, search string) from standard input.
   * @param args Command line arguments
   */
  public static void main(String[] args) {
      Instant start = Instant.now();
      Scanner sc = new Scanner(System.in);
      ArrayList<CompletableFuture<String>> descriptions = new ArrayList<>();
      while (sc.hasNext()) {
          BusStop srcId = new BusStop(sc.next());
          String searchString = sc.next();

          CompletableFuture<String> routeInfo = BusSg
              .findBusServicesBetween(srcId,searchString)
              .thenCompose(BusRoutes::description);
          descriptions.add(routeInfo);
      }
      sc.close();

      CompletableFuture<?>[] joined = descriptions.toArray(CompletableFuture<?>[]::new);
      CompletableFuture.allOf(joined).join();

      for (int i=0; i < joined.length; i++) {
          joined[i].thenAccept(System.out::println);
      }

      Instant stop = Instant.now();
      System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
  }
}
