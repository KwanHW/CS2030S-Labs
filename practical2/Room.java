import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Room {
    private final List<Thing> objects;
    private final String room;

    public Room(String room) {
        this(room,new ArrayList<Thing>());
    }

    public Room(String room,List<Thing> objects) {
        this.room = room;
        this.objects = objects;
    }

    public Room add(Thing thing) {
        ArrayList<Thing> newList = new ArrayList<>();
        newList.addAll(objects);
        newList.add(thing);
        return new Room(this.room, newList);
    }

    // TODO May change implementation to a List
    // Use replaceAll
    public Room tick() {
        return new Room(this.room,
        this.objects.stream()
            .map(Thing::tick)
            .collect(Collectors.toList()));
    }

    public Room tick(Function<Thing,Thing> f) {
        return new Room(this.room,
        this.objects.stream()
            .map(x->f.apply(x))
            .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        String msg = String.format("@%s",this.room);
        Iterator<Thing> obIt = this.objects.iterator();
        while(obIt.hasNext()) {
            String str = "\n" + obIt.next().toString();
            msg += str;
        }
        return msg;
    }
}
