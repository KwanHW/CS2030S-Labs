import java.util.function.Function;
import java.util.function.Predicate;
import java.util.List;

public interface Logger<T> {
    public static <T> Logger<T> make(T value) {
        if (value == null) {
            throw new IllegalArgumentException("argument cannot be null");
        } else if (value instanceof Logger) {
            throw new IllegalArgumentException("already a Logger");
        }
        return new LoggerImpl<T>(value);
    }

    public abstract boolean test(Predicate<? super T> pred);

    public abstract <U> Logger<U> map(Function<? super T,? extends U> f);

    public abstract <U> Logger<U> flatMap(Function<? super T,? extends Logger<? extends U>> f);

    public abstract void printlog();

    public abstract List<String> getLogs();

    public abstract T getValue();
}
