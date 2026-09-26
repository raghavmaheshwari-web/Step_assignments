package week8.q2_parcel;

/**
 * Express shipping: ₹80 plus ₹15 per kg.
 */
public class ExpressShipping implements ShippingType {
    private static final double BASE_FEE = 80.0;
    private static final double RATE_PER_KG = 15.0;

    @Override
    public String getName() {
        return "Express";
    }

    @Override
    public double calculateCharge(double weightKg) {
        return BASE_FEE + (RATE_PER_KG * weightKg);
    }
}
