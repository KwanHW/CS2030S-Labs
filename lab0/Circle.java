class Circle{
    private final Point centre;
    private final double radius;

    Circle(Point centre, double radius){
        this.centre = centre;
        this.radius = radius;
    }
    
    boolean contains(Point point){
        return centre.distanceTo(point) <= radius;
    }

    public Point getCentre() {
        return centre;
    }

    @Override
    public String toString(){
        return String.format("circle of radius %.1f centered at " + this.centre ,this.radius);
    }
}
