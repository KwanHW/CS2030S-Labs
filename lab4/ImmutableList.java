import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.function.Function;

class ImmutableList<T> {
    private final List<T> list;

    @SafeVarargs
    ImmutableList(T...list) {
        this.list = List.of(list);
    }

    ImmutableList(List<T> list) {
        this.list = List.copyOf(list);
    }

    public List<T> get() {
        return this.list;
    }

    // List is immutable hence need to use ArrayList<T> to make the changes
    ImmutableList<T> add(T element) {
        ArrayList<T> newList = new ArrayList<T>(this.list); 
        newList.add(element);
        return new ImmutableList<T>(newList);
    }

    ImmutableList<T> remove(T element) {
        ArrayList<T> newList = new ArrayList<T>(this.list); 
        newList.remove(element);
        return new ImmutableList<T>(newList);
    }

    ImmutableList<T> replace(T src, T replace) {
        ArrayList<T> newList = new ArrayList<T>(this.list);
        // Collections replaceAll can be used without implementing a UnaryOperator
        Collections.replaceAll(newList,src,replace);
        return new ImmutableList<T>(newList);
    } 

    ImmutableList<T> limit(long limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit size < 0");
        } 

        // If the limit is bigger than the size of List
        if (limit > this.list.size()) {
            return new ImmutableList<T>(this.list.subList(0,this.list.size()));
        } else {
            // If the limit is 0 it will be here too
            return new ImmutableList<T>(this.list.subList(0,(int)limit));
        }
    } 

    public boolean equals(ImmutableList<T> comp) {
        return this.list.equals(comp.get());
    }

    public ImmutableList<T> sorted(Comparator<T> comp) {
        if (comp == null) {
            throw new NullPointerException("Comparator is null");
        }

        ArrayList<T> newList = new ArrayList<T>(this.list);
        newList.sort(comp);
        return new ImmutableList<T>(newList);
    }

    public Object[] toArray() {
        return this.list.toArray();
    }

    //Generic method
    public <T> T[] toArray(T[] t) {
        // Based on List.toArray(T[] a) documentation
        // Use Exceptions thrown by the toArray()
        try {
            return this.list.toArray(t);
        } catch (ArrayStoreException e) {
            throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
        } catch (NullPointerException e) {
            throw new NullPointerException("Input array cannot be null");
        }
    }

    // <? super T> as the method cannot guarantee that T is compatible. Hence adding the wildcard 
    // sets the 'scope' of that T is, ensuring the compatibility of type arguments
    public ImmutableList<T> filter(Predicate<? super T> pred) {
        // Converts the list into a stream and uses the stream.filter to execute the predicate
        List<T> filterList = List.copyOf(this.list).stream().filter(pred).collect(Collectors.toList());
        return new ImmutableList<T>(filterList);
    }

    /* ? super T so that it can possibly be converted to R (if above the hierachy)
    EXAMPLE: T is String 
    ? super T allows T to be String and above(to Object) and 
    hence allow it to be converted to R(Integer)
    */
    public <R> ImmutableList<R> map(Function<? super T,? extends R> func) {                       
        List<R> list = List.copyOf(this.list).stream().map(func).collect(Collectors.toList());
        return new ImmutableList<R>(list);                                      
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}
