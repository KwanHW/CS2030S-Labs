import java.util.List;
import java.util.ArrayList;

public class Person {
    private final String name;
    private final List<Virus> virusList;
    private final double endOfSHN;

    public Person(String name,List<Virus> virusList,double endOfSHN){
        this.name = name;
        this.virusList = virusList;
        this.endOfSHN = endOfSHN;
    }

    public Person(String name, List<Virus> virusList) {
        this(name, virusList,0);
    }

    public Person(String name) {
        this(name, new ArrayList<Virus>(),0);
    }

    public String getName() {
        return this.name;
    }

    public List<Virus> getViruses() {
        return this.virusList;
    }
    
    public double getEndOfSHN() {
        return this.endOfSHN;
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

    public Person issuedSHN(double endOfSHN) {
        return new Person(this.name,this.virusList,endOfSHN);
    }

    public boolean test(String name) {
        for(Virus v : this.virusList) {
            if (v.getName() == name) {
                return true;
            }
        }
        return false;
    }

    // Checks if person is on SHN at specified point in time
    public boolean onSHN (double currentTime) {
        return currentTime < endOfSHN;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
