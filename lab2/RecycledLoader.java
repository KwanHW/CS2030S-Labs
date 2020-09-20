public class RecycledLoader extends Loader {

    RecycledLoader(int identifier, Cruise cruise) {
        super(identifier,cruise);
    }

    @Override
    public RecycledLoader serve(Cruise c) {
        // Pretty sure this is wrong, as its code duplication.
        // Is there a way I can still use Loader.serve() 
        // method and return RecycledLoader?
        if (this.canServe(c)) {
            return new RecycledLoader(this.getIdentifier(), c);
        } else {
            return this;
        }
    }

    @Override
    public int getNextAvailableTime() {
        // After every job it has to go through an additional 60min maintenance
        return super.getNextAvailableTime() + 60; 
    }

    @Override
    public String toString() {
        return String.format("Recycled %s",super.toString());
    }

}
