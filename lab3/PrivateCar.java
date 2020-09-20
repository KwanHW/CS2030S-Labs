public class PrivateCar extends Driver {

    public PrivateCar(String licensePlate, int waitTime) {
        super(licensePlate,waitTime,new Service[]{new JustRide(), new ShareARide()});
    }

    @Override
    public String toString() {
        return super.toString() + " PrivateCar";
    }
}
