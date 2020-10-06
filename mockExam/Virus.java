public abstract class Virus {
    private final String name;
    private final double probabilityOfMutating;

    public Virus(String name, double probabilityOfMutating) {
        this.name = name;
        this.probabilityOfMutating = probabilityOfMutating;
    }

    public String getName() {
        return this.name;
    }

    public double getProbability() {
        return this.probabilityOfMutating;
    }

    public abstract Virus spread(double random);
    @Override
    public String toString() {
        return String.format("%s with %.3f probability of mutating",this.name,this.probabilityOfMutating);
    }
}
