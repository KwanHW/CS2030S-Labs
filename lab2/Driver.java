public class Driver {
    private final String licensePlate;
    private final int waitTime;
    private final Service[] servicesProvided;

    public Driver(String licensePlate,int waitTime,Service[] servicesProvided) {
        this.licensePlate = licensePlate;
        this.waitTime = waitTime;
        this.servicesProvided = servicesProvided;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public Service[] getServicesProvided() {
        return servicesProvided;
    }

    public Service compareFares(Request r) {
        Service selected = this.getServicesProvided()[0];
        for (Service s : this.getServicesProvided()) {
            // If the fare is cheaper than the selected service 
            if (s.computeFare(r) < selected.computeFare(r)) {
                selected = s;
            }
        }

        return selected;
    }

    @Override
    public String toString() {
        return String.format("%s (%d mins away)",this.licensePlate,this.waitTime);
    }
}
