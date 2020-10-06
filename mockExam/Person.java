import java.util.List;
import java.util.ArrayList;

public class Person {
    private final String name;
    private final List<Virus> virusList;

    public Person(String name, List<Virus> virusList){
        this.name = name;
        this.virusList = virusList;
    }

    public Person(String name) {
        this(name, new ArrayList<Virus>());
    }

    public String getName() {
        return this.name;
    }

    public List<Virus> getViruses() {
        return this.virusList;
    }

    public List<Virus> transmit(double random) {
        List<Virus> vList = new ArrayList<>();
        for(Virus v : this.virusList){
            v = v.spread(random);
            vList.add(v);
        }
        return vList;
    }

    public Person infectWith(List<Virus> listOfViruses, double random) {
        return new Person(name, listOfViruses);
    }

    public boolean test(String name) {
        for(Virus v : this.virusList) {
            if (v.getName() == name) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
