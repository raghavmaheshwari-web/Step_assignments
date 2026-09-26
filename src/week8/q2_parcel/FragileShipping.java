package week8.q2_parcel;

/**
 * Fragile shipping: reuses Standard shipping via composition and adds a ₹50 handling fee.
 */
public class FragileShipping implements ShippingType {
    private final StandardShipping standardShipping = new StandardShipping();
    private static final double HANDLING_FEE = 50.0;

    @Override
    public String getName() {
        return "Fragile";
    }

    @Override
    public double calculateCharge(double weightKg) {
        return standardShipping.calculateCharge(weightKg) + HANDLING_FEE;
    }
}
