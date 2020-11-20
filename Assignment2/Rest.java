/**
 * Rest
 */

package cs2030.simulator;

import java.util.function.Supplier;

public class Rest {
    private final Supplier<Double> restChance;
    private final Supplier<Double> restDuration;
    private final double restProb;

    /**
     * Constructs a Rest object to handle the computation of resting probabilities
     * and duration of rest.
     * @param restChance The specified supplier which generates the probability of rest
     * @param restDuration The speicified supplier which generates the duration of rest
     * @param restProb The speicified probability to determine if a server is going to rest 
     */
    public Rest(Supplier<Double> restChance,Supplier<Double> restDuration,
            double restProb) {
        this.restChance = restChance;
        this.restDuration = restDuration;
        this.restProb = restProb;
    }

    public static Rest empty() {
        return new Rest(() -> 0.0,() -> 0.0,0);
    }

    public boolean goingToRest() {
        return this.restChance.get() < this.restProb;
    }

    public double getRestDuration() {
        return this.restDuration.get();
    }

    
}
