import java.util.List;
import java.util.function.UnaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.Collectors;

// Correct attributes?
public class LazyList<T extends Comparable<T>> {
    // These attributes will be the parameters used to create the Streams
    private final List<Lazy<T>> lazyList;

    private LazyList(List<Lazy<T>> lazyList) {
        this.lazyList = lazyList;
    }

    public static <T extends Comparable<T>> LazyList<T> generate(int n,T seed,UnaryOperator<T> f) {
       return new LazyList<>(
               Stream.iterate(Lazy.of(seed),x->x.map(f))
               .limit(n)
               .collect(Collectors.toList()));
    }

    // Gets the n-th index of a Lazy
    public T get(int i) {
        return this.lazyList.stream()
            .skip(i) // Skips the n-1 elements
            .findFirst() // Takes the first Element (nth element)
            .orElseThrow() // Returns the Lazy
            .get(); // Returns the value itself
    }

    public int indexOf(T value) {
        // Predicate to check if Lazy value equals to value
        Predicate<T> isValue = x -> x.compareTo(value) == 0;
        return (int) this.lazyList.stream()
            // takeWhile will continue to go down the stream until it hits a false
            .takeWhile(x -> x.test(isValue.negate()).get())
            // Value is the last element of the returned Stream
            .count();
    }
}
