package week7;

/**
 * ShapeAreaPrinter demonstrates polymorphic method invocation on abstract Shape references.
 */
public class ShapeAreaPrinter {

    /**
     * Prints the calculated area of any Shape polymorphically without inspecting subclass type.
     *
     * @param s shape instance
     */
    public static void printArea(Shape s) {
        if (s == null) {
            System.out.println("Shape reference is null.");
            return;
        }
        System.out.println("Shape " + s.getShapeId() + " Area: " + String.format("%.2f", s.calculateArea()));
    }
}
