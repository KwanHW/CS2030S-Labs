public class Instructor {
    private final String name;

    public Instructor(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Instructor) {
            Instructor ins = (Instructor) obj;
            return this.name == ins.getName();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}

