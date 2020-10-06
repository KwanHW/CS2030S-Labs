import java.util.List;
import java.util.ArrayList;

public class MaskedPerson extends Person {
    public MaskedPerson(String name) {
        super(name);
    }

    public MaskedPerson(String name, List<Virus> virusList){
        super(name,virusList);
    }

    public MaskedPerson(String name,List<Virus> virusList,boolean onSHN,double endOfSHN){
        super(name,virusList,endOfSHN);
    }

    @Override
    public List<Virus> transmit(double random) {
        if (random > SimulationParameters.MASK_EFFECTIVENESS) {
            return super.transmit(random);
        } else {
            return List.of();
        }
    }

    @Override
    public Person infectWith(List<Virus> listOfViruses, double random) {
        if (random > SimulationParameters.MASK_EFFECTIVENESS) {
            return new MaskedPerson(super.getName(), listOfViruses);
        } else {
            return this;
        }
    }

}

