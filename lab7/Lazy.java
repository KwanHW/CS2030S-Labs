import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lazy<T extends Comparable<T>> {
    private Optional<T> value;
    private boolean hasBeenRun;
    private final Supplier<T> supp;

    private Lazy(Optional<T> value, Supplier<T> supp) {
        this.value = value;
        this.supp = supp;
        this.hasBeenRun = false;
    }

    private Lazy(Supplier<T> supp) {
        this(Optional.empty(),supp);
    }

    public T get() {
        if (!hasBeenRun) {
            T value = supp.get();
            this.hasBeenRun = !hasBeenRun;
            // If the value is null a NullPointerException will be thrown
            this.value = Optional.of(value);
        }
        return value.orElseThrow();
    }

    public static <T extends Comparable<T>> Lazy<T> of(T value) {
        return new Lazy<>(Optional.of(value),()->value);
    }

    public static <T extends Comparable<T>> Lazy<T> of(Supplier<T> supp) {
        try {
            // If it is null exception will be thrown
            Optional.of(supp); 
            return new Lazy<>(supp);
        } catch (Exception e) {
            throw new NoSuchElementException("No value present");
        }
    }

    public <U extends Comparable<U>> Lazy<U> map(Function<? super T,? extends U> f) {
        return new Lazy<U>(() -> f.apply(this.get()));
    }

    public <U extends Comparable<U>> Lazy<U> flatMap(Function<? super T,? extends Lazy<U>> f) {
        return new Lazy<>(()->f.apply(this.get()).get());
    }

    // Should NOT be evaluated eagerly
    // Am I really sure that its supposed to be done this way?
    public <U extends Comparable<U>,R extends Comparable<R>> Lazy<R> combine(Lazy<U> other,BiFunction<? super T, ? super U,? extends R> bifunc) {
        return new Lazy<R>(() -> bifunc.apply(this.get(),other.get()));
    }

    public Lazy<Boolean> test(Predicate<T> pred) { 
        return Lazy.of(pred.test(this.get()));
    }

    public Lazy<Integer> compareTo(Lazy<T> other) {
        return Lazy.of(this.get().compareTo(other.get()));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Lazy) {
            Lazy<?> lazyObj = (Lazy<?>)obj;
            return this.get().equals(lazyObj.get());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (!hasBeenRun) {
            return "?";
        } else {
            return this.get().toString();
        }
    }
}
