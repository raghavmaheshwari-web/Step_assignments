package week7;

/**
 * Problem 1: Basic Drawing Canvas
 *
 * Abstract base class Shape demonstrating:
 * 1. An abstract method calculateArea() requiring specialized subclass implementation.
 * 2. An immutable final shapeId initialized from a shared static counter in the constructor.
 * 3. Overloaded concrete methods scale(factor) and scale(xFactor, yFactor) demonstrating
 *    compile-time polymorphism.
 */
public abstract class Shape {
    private static int shapeCounter = 0;
    private final String shapeId;

    public Shape() {
        shapeCounter++;
        this.shapeId = "SHAPE-" + shapeCounter;
    }

    public String getShapeId() {
        return shapeId;
    }

    public static int getShapeCounter() {
        return shapeCounter;
    }

    public static void resetShapeCounter() {
        shapeCounter = 0;
    }

    /**
     * Abstract method implemented by specific shapes.
     *
     * @return computed area
     */
    public abstract double calculateArea();

    /**
     * Internal scaling hook implemented by subclasses to scale their respective dimensions.
     *
     * @param xFactor horizontal scaling factor
     * @param yFactor vertical scaling factor
     */
    protected abstract void applyScale(double xFactor, double yFactor);

    /**
     * Concrete overloaded scale method for uniform scaling.
     *
     * @param factor uniform scaling multiplier
     */
    public void scale(double factor) {
        scale(factor, factor);
    }

    /**
     * Concrete overloaded scale method for uneven 2D scaling.
     *
     * @param xFactor horizontal scaling factor
     * @param yFactor vertical scaling factor
     */
    public void scale(double xFactor, double yFactor) {
        applyScale(xFactor, yFactor);
    }
}
