package week4;

/**
 * Problem 1: Ghost Order Validator
 *
 * Enforces strict object construction invariants by providing only a parameterized
 * constructor that rejects null, empty, or whitespace-only student and dish names.
 * Tracks delivery status and processes raw order batches.
 */
public class FoodOrder {
    private final String studentName;
    private final String dishName;
    private boolean isDelivered;

    /**
     * Parameterized constructor that validates studentName and dishName.
     * Prevents ghost orders with missing or whitespace-only values.
     *
     * @param studentName student placing the order
     * @param dishName name of the ordered dish
     * @throws IllegalArgumentException if either parameter is null or blank
     */
    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid student name: name cannot be null, empty, or whitespace-only.");
        }
        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid dish name: dish cannot be null, empty, or whitespace-only.");
        }
        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
        this.isDelivered = false;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDishName() {
        return dishName;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    /**
     * Marks the order as delivered.
     * Prints a warning if the order has already been delivered to alert the kitchen of duplicate delivery.
     */
    public void markDelivered() {
        if (isDelivered) {
            System.out.println("WARNING: Order for " + studentName + " (" + dishName + ") was already marked delivered!");
        } else {
            this.isDelivered = true;
            System.out.println("Order for " + studentName + " (" + dishName + ") successfully marked as delivered.");
        }
    }

    /**
     * Reads a batch of raw order attempts {studentName, dishName} and reports
     * how many were accepted and how many were rejected.
     *
     * @param rawOrders 2D array of raw order strings
     */
    public static void processBatch(String[][] rawOrders) {
        if (rawOrders == null) {
            System.out.println("Valid: 0 | Rejected: 0");
            return;
        }

        int validCount = 0;
        int rejectedCount = 0;

        for (String[] rawOrder : rawOrders) {
            if (rawOrder == null || rawOrder.length < 2) {
                rejectedCount++;
                continue;
            }

            try {
                new FoodOrder(rawOrder[0], rawOrder[1]);
                validCount++;
            } catch (IllegalArgumentException ex) {
                rejectedCount++;
            }
        }

        System.out.println("Valid: " + validCount + " | Rejected: " + rejectedCount);
    }

    public static void main(String[] args) {
        System.out.println("--- Batch Validation Demo ---");
        String[][] rawOrders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };
        processBatch(rawOrders);

        System.out.println("\n--- Delivery State Demo ---");
        FoodOrder order = new FoodOrder("Ravi", "Paneer Butter Masala");
        order.markDelivered(); // First delivery
        order.markDelivered(); // Second delivery (double delivery alert)
    }
}
