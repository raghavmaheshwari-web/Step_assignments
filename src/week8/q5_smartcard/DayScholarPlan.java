package week8.q5_smartcard;

/**
 * Day Scholar plan: pays full price (0% discount).
 */
public class DayScholarPlan implements PricingPlan {
    @Override
    public String getName() {
        return "Day Scholar plan";
    }

    @Override
    public double computePrice(double basePrice) {
        return basePrice;
    }
}
