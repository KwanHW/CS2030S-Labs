public class Booking {
    private final Driver driver;
    private final Request request;
    private final Service service;

    public Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request;
        this.service = compareFares(request);
    }

    public Driver getDriver() {
        return driver;
    }

    public double toDollars() {
        return (double)service.computeFare(request) / 100;
    }

    public int compareTo(Booking b) {
        // If both have the same amount, we will compare their waiting times
        if (this.toDollars() == b.toDollars()) {
            return this.driver.getWaitTime() - b.getDriver().getWaitTime();
        } else {
            // Check if this booking is cheaper than the other
            return (int)(this.toDollars() - b.toDollars());
        }
    }

    public Service compareFares(Request r) {
        Service selected = driver.getServicesProvided()[0];
        for (Service s : driver.getServicesProvided()) {
            // If the fare is cheaper than the selected service 
            if (s.computeFare(r) < selected.computeFare(r)) {
                selected = s;
            }
        }

        return selected;
    }

    @Override
    public String toString() {
        return String.format("$%.2f using %s (%s)",toDollars(),driver,service);
    }
    
}
