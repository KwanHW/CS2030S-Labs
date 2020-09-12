public class NormalCab extends Driver {

    public NormalCab(String licensePlate, int waitTime) {
        super(licensePlate,waitTime,new Service[]{new JustRide(), new TakeACab()});
    }

    @Override
    public String toString() {
        return super.toString() + " NormalCab";
    }
}
