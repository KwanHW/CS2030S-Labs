public class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double getX() {
        return this.x;
    }

    double getY() {
        return this.y;
    }

    Point midPoint(Point q) {
        double midX = (this.x + q.getX()) / 2;
        double midY = (this.y + q.getY()) / 2;
        return new Point(midX, midY);
    }

    double angleTo(Point q) { 
        // Check if they are same points
        if (this.equals(q)) {
            return 0;
        }

        // Get the lengths of opp and adj
        double adj = q.getX() - this.x;
        double opp = q.getY() - this.y;

        // Check if could be 0, pi/2, pi, -pi/2 
        if (opp == 0 && adj == 0) {
            return 0;
        }

        if (opp == 0) {
            if (adj > 0) {
                return 0; 
            } else {
                return Math.PI;
            }
        } else if (adj == 0) {
            if (opp > 0) {
                return Math.PI / 2;
            } else {
                return -Math.PI / 2; 
            }
        }

        // Get the basic angle value
        return Math.atan2(opp, adj);
    }

    double distanceTo(Point point) {
        double dx = this.x - point.getX();
        double dy = this.y - point.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Assume theta is in radians
    Point moveTo(double theta, double d) {
        double newX = this.x + (d * Math.cos(theta));
        double newY = this.y + (d * Math.sin(theta));
        return new Point(newX, newY);
    }

    // Output returns the points of Point object
    @Override
    public String toString() {
        return String.format("point (%.3f, %.3f)",this.x,this.y);
    }

    // Checks if the same points are the same value
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Point) {
            Point p = (Point) obj;
            return Math.abs(this.x - p.getX()) < 1E-15 && Math.abs(this.y - p.getY()) < 1E-15;
        } else {
            return false;
        }
    }
}
