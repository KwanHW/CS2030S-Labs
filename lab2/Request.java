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
        return passenger;
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs",this.distance, this.passenger, this.time);
    }
}
