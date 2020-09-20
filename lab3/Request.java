public class Request {
    private final int distance;
    private final int passenger;
    private final int time;

    public Request(int distance, int passenger, int time) {
        this.distance = distance;
        this.passenger = passenger;
        this.time = time;
    }

    public int getTime() {
        return this.time;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getPassenger() {
        return this.passenger;
    }

    public boolean isPeakHours(){
        return (600 <= time && time <= 900);
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs",this.distance, this.passenger, this.time);
    }
}
