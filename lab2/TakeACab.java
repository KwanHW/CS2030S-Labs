public class TakeACab extends Service {
    public TakeACab() {
       super(33,200); 
    }

    @Override
    public int computeFare(Request r) {
        int fare = r.getDistance() * this.getFare() + this.getBookingFee();
        return fare;
    }

    @Override
    public String toString() {
        return "TakeACab";
    }

}

