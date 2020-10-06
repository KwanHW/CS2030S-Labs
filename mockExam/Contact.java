import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Contact {
    private final Person first;
    private final Person second;
    private final double time;

    public Contact(Person first, Person second, double time) {
        this.first = first;
        this.second = second;
        this.time = time;
    }

    public double timeOfContact() {
        return this.time;
    }

    public List<Person> transmit(double random) {
        List<Virus> firstVirus = new ArrayList<>();
        firstVirus.addAll(this.first.getViruses());
        firstVirus.addAll(this.second.transmit(random));

        List<Virus> secondVirus = new ArrayList<>();
        secondVirus.addAll(this.second.getViruses());
        secondVirus.addAll(this.first.transmit(random));

        List<Person> perList = new ArrayList<>();
        perList.add(this.first.infectWith(firstVirus,random));
        perList.add(this.second.infectWith(secondVirus,random));

        return perList;
    }

    public List<Person> getPeople() {
        return new ArrayList<>(Arrays.asList(this.first,this.second));
    }
}

