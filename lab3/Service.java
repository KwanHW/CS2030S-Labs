public abstract class Service {
    // In cents
    private final int fare; 
    private final int bookingFee;

    public Service(int fare,int bookingFee) {
        this.fare = fare;
        this.bookingFee = bookingFee;
    }

    public int getFare() {
        return this.fare;
    }

    public int getBookingFee() {
        return bookingFee;
    }
    
    public abstract int computeFare(Request r);
}
