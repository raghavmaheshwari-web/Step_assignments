package week8.q2_parcel;

/**
 * Standard shipping: ₹40 plus ₹10 per kg.
 */
public class StandardShipping implements ShippingType {
    private static final double BASE_FEE = 40.0;
    private static final double RATE_PER_KG = 10.0;

    @Override
    public String getName() {
        return "Standard";
    }

    @Override
    public double calculateCharge(double weightKg) {
        return BASE_FEE + (RATE_PER_KG * weightKg);
    }
}
