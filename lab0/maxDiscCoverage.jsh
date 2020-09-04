Circle createUnitCircle(Point p, Point q){
    // Move midpoint to the radius (perimeter coincides with points p & q)
    Point mid = p.midPoint(q);

    // Use Pythagorean Theorem to find d
    double d = Math.sqrt(1 - Math.pow(p.distanceTo(mid), 2));

    // Create the centre point
    Point centre = mid.moveTo((p.angleTo(q)+Math.PI/2), d);

    // Return the new circle
    return new Circle(centre, 1);
}

// Using intance of circle and with the array of pts, count number of pts inside the circle
int findMaxDiscCoverage(Point[] points){
    int maxDiscCoverage = 0;

    // find coverage with points[i],points[j]
    for (int i =0; i < points.length -1; i++){
        for (int j=i+1; j < points.length; j++){
            // If the distance between pts > 2, there is no unit circle 
            // where perimeter coincides with the points
            // if (points[i].distanceTo(points[j]) >= 2)
                // continue;

            int count= 0;
            Circle c = createUnitCircle(points[i], points[j]);

            for (Point p : points){
                if (c.contains(p))
                    count++;
            }

            if (count > maxDiscCoverage)
                maxDiscCoverage = count;
        }
    }
    return maxDiscCoverage;
}
