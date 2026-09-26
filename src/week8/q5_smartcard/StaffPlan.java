package week8.q5_smartcard;

/**
 * Staff plan: receives 20% discount on food items.
 */
public class StaffPlan implements PricingPlan {
    @Override
    public String getName() {
        return "Staff plan";
    }

    @Override
    public double computePrice(double basePrice) {
        return basePrice * 0.80;
    }
}
