package week4;

/**
 * Problem 4: Exam-Week Surge Fee Calculator
 *
 * Demonstrates the 'final' keyword applied at three architectural levels:
 * 1. final class: Prevents extending the calculator and tampering with fee rules.
 * 2. final field: Guarantees immutable configuration for minimum surge floor.
 * 3. final method: Locks the calculation logic against override.
 */
public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    /**
     * Constructs a calculator with an immutable minimum surge floor percentage.
     *
     * @param minimumSurgePercent floor percentage applied to delayed orders (e.g. 1.0 for 1%)
     */
    public SurgeFeeCalculator(double minimumSurgePercent) {
        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException("Minimum surge percent cannot be negative.");
        }
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public double getMinimumSurgePercent() {
        return minimumSurgePercent;
    }

    /**
     * Calculates the tiered surge fee in O(1) closed-form time.
     *
     * Tier structure on additional delay minutes:
     * - Minutes 1–5:  0.5% of orderValue per minute
     * - Minutes 6–15: 1.0% of orderValue per minute
     * - Minutes 16+:  2.0% of orderValue per minute
     *
     * Minimum floor applies ONLY if delayMinutes > 0.
     * On-time orders (delayMinutes == 0) are strictly exempt from surge fees.
     *
     * @param orderValue monetary value of the food order
     * @param delayMinutes minutes delayed past normal wait time
     * @return surge fee in rupees
     * @throws IllegalArgumentException if orderValue or delayMinutes is negative
     */
    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0) {
            throw new IllegalArgumentException("Order value cannot be negative: " + orderValue);
        }
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay minutes cannot be negative: " + delayMinutes);
        }

        // On-time orders incur no surge fee
        if (delayMinutes == 0) {
            return 0.0;
        }

        // O(1) closed-form calculation of minutes falling into each tier
        int bracket1Minutes = Math.min(delayMinutes, 5);
        int bracket2Minutes = Math.max(0, Math.min(delayMinutes - 5, 10));
        int bracket3Minutes = Math.max(0, delayMinutes - 15);

        double rateBracket1 = 0.005; // 0.5% per minute
        double rateBracket2 = 0.010; // 1.0% per minute
        double rateBracket3 = 0.020; // 2.0% per minute

        double tieredRate = (bracket1Minutes * rateBracket1)
                          + (bracket2Minutes * rateBracket2)
                          + (bracket3Minutes * rateBracket3);

        double tieredFee = tieredRate * orderValue;

        // Apply minimum surge floor
        double floorAmount = (minimumSurgePercent / 100.0) * orderValue;
        return Math.max(tieredFee, floorAmount);
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calculator = new SurgeFeeCalculator(1.0); // 1.0% minimum surge floor

        System.out.println("orderValue = 500, delayMinutes = 0  -> Rs " + calculator.calculateSurgeFee(500, 0));
        System.out.println("orderValue = 500, delayMinutes = 1  -> Rs " + calculator.calculateSurgeFee(500, 1));
        System.out.println("orderValue = 500, delayMinutes = 16 -> Rs " + calculator.calculateSurgeFee(500, 16));
    }
}
