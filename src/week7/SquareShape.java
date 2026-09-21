package week7;

/**
 * SquareShape extends Shape directly and implements calculateArea() using square geometry.
 */
public class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        super();
        this.side = Math.max(0.0, side);
    }

    public double getSide() {
        return side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    protected void applyScale(double xFactor, double yFactor) {
        this.side *= xFactor;
    }
}
