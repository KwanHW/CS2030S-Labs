public class SARS_CoV_2 extends Virus {
    public SARS_CoV_2(double probability) {
        super("SARS-CoV-2", probability);
    }

    @Override
    public Virus spread(double random) {
        if (random <= super.getProbability()) {
            return new BetaCoronavirus();
        } else {
            return new SARS_CoV_2(super.getProbability() * SimulationParameters.VIRUS_MUTATION_PROBABILITY_REDUCTION);
        }
    }
}
