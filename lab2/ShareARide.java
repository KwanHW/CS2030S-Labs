public class ShareARide extends Service {
    public ShareARide() {
       super(50,0); 
    }

    @Override
    public int computeFare(Request r) {
        int fare = r.getDistance() * this.getFare();
        if (isPeakHours(r)) {
            fare += 500;
        }
        return fare / r.getPassenger();
    }

    @Override
    public boolean isPeakHours(Request r) {
        return (600 <= r.getTime() && r.getTime() <= 900);
    }

    @Override
    public String toString() {
        return "ShareARide";
    }

}

