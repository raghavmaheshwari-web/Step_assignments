package week8.q5_smartcard;

/**
 * Hosteller plan: receives 10% discount on food items.
 */
public class HostellerPlan implements PricingPlan {
    @Override
    public String getName() {
        return "Hosteller plan";
    }

    @Override
    public double computePrice(double basePrice) {
        return basePrice * 0.90;
    }
}
