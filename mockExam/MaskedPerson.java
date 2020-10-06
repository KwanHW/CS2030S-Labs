public class MaskedPerson extends Person {
    public MaskedPerson(String name) {
        super(name);
    }

    public List<Virus> transmit(double random) {
        List<Virus> vList = new ArrayList<>();
        for(Virus v : this.virusList){
            v = v.spread(random);
            vList.add(v);
        }
        return vList;
    }

}

