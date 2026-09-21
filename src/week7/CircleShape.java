package week7;

/**
 * CircleShape extends Shape directly and implements calculateArea() using circle geometry.
 */
public class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        super();
        this.radius = Math.max(0.0, radius);
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    protected void applyScale(double xFactor, double yFactor) {
        this.radius *= (xFactor + yFactor) / 2.0;
    }
}
