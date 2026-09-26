package week8.q5_smartcard;

/**
 * Strategy interface for plan-based item pricing in the canteen.
 */
public interface PricingPlan {
    String getName();
    double computePrice(double basePrice);
}
