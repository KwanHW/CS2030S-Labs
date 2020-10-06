import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Location {
    private final String name;
    private final List<Person> occupants;

    public Location(String name, List<Person> occupants) {
        this.name = name;
        this.occupants = occupants;
    }

    public Location(String name) {
        this(name, new ArrayList<Person>());
    }

    public List<Person> getOccupants() {
        return this.occupants;
       }

    public Location accept(Person person) {
        List<Person> pList = new ArrayList<>();
        pList.addAll(this.occupants);
        pList.add(person);
        return new Location(this.name, pList);
    }

    public Location remove(String personName) {
        List<Person> pList = new ArrayList<>(List.copyOf(this.occupants));
        Iterator<Person> itPerson = this.occupants.iterator();
        while (itPerson.hasNext()) {
            Person p = itPerson.next();
            if (p.getName() == personName) {
                pList.remove(p);
                break;
            }
        }
        return new Location(this.name,pList);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
