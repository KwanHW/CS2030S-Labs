
package cs2030.simulator;

public class RandomGen {
    private final RandomGenerator rand;

    public RandomGen(int seed,double lambda,double mu,double rho) {
        this.rand = new RandomGenerator(seed,lambda,mu,rho);
    }

    public double getArrival() {
        return rand.genInterArrivalTime();
    }

    public double getService() {
        return rand.genServiceTime();
    }

    public double getRandomRest() {
        return rand.genRandomRest();
    }

    public double getRestPeriod() {
        return rand.genRestPeriod();
    }

    public double getCustomerType() {
        return rand.genCustomerType();
    }
}

