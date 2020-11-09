import java.util.List;

// Possible to think of an enum to use
// Gonna get majorly downgraded but just try to make good the imp
public class Thing {
    private final List<String> events;
    private final int state;

    public Thing(List<String> events,int state) {
        this.events = events;
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    public Thing tick() {
        if (state+1 == events.size()) {
            return this;
        }
        return new Thing(events,state+1);
    }

    @Override
    public String toString() {
        return this.events.get(state);
    }
}

        
