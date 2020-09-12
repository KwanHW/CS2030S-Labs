public class JustRide extends Service {
    public JustRide() {
       super(22,0); 
    }

    @Override
    public int computeFare(Request r) {
        int fare = r.getDistance() * this.getFare();
        if (r.isPeakHours()) {
            fare += 500;
        }

        return fare;
    }

    @Override
    public String toString() {
        return "JustRide";
    }

}

