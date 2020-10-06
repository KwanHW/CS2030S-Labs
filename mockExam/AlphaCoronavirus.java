public class AlphaCoronavirus extends Virus {
    public AlphaCoronavirus(double probability) {
        super("Alpha Coronavirus", probability);
    }

    @Override
    public Virus spread(double random) {
        if (random <= super.getProbability()) {
            return new SARS_CoV_2(super.getProbability());
        } else {
            return new AlphaCoronavirus(super.getProbability() * SimulationParameters.VIRUS_MUTATION_PROBABILITY_REDUCTION);
        }
    }
}
