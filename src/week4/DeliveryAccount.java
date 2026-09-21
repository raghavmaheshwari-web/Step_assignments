package week4;

/**
 * DeliveryAccount model for student food delivery billing.
 * Demonstrates:
 * 1. Static initialization block for class-level configuration.
 * 2. Parameterized and provisional constructor chaining with this(...).
 * 3. Final method calculateSurgeFee reusing Problem 4's SurgeFeeCalculator.
 */
public class DeliveryAccount {
    protected static SurgeFeeCalculator defaultCalculator;

    // Static initialization block for one-time class-level configuration
    static {
        defaultCalculator = new SurgeFeeCalculator(1.0); // 1.0% minimum surge floor
    }

    private final String studentId;
    private final double orderValue;

    /**
     * Primary constructor initializing studentId and orderValue.
     */
    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = (studentId != null) ? studentId.trim() : "";
        this.orderValue = Math.max(0.0, orderValue);
    }

    /**
     * Provisional constructor chaining via this(...) with zero default order value.
     */
    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public String getStudentId() {
        return studentId;
    }

    public double getOrderValue() {
        return orderValue;
    }

    /**
     * Final calculation of surge fee for this account based on delay minutes.
     * Reuses Problem 4's SurgeFeeCalculator.
     */
    public final double calculateSurgeFee(int delayMinutes) {
        return defaultCalculator.calculateSurgeFee(this.orderValue, delayMinutes);
    }
}
