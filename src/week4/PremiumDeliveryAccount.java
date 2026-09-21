package week4;

/**
 * PremiumDeliveryAccount extends DeliveryAccount.
 * Represents premium campus subscribers who receive specialized surge fee settlement
 * (e.g. 50% discount on tiered surge charges).
 */
public class PremiumDeliveryAccount extends DeliveryAccount {
    public static final double PREMIUM_DISCOUNT_FACTOR = 0.50; // 50% discount

    public PremiumDeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public PremiumDeliveryAccount(String studentId) {
        super(studentId);
    }

    /**
     * Calculates the discounted surge fee for a premium subscriber.
     *
     * @param delayMinutes minutes of delay
     * @return discounted surge fee
     */
    public double calculatePremiumSurgeFee(int delayMinutes) {
        return calculateSurgeFee(delayMinutes) * PREMIUM_DISCOUNT_FACTOR;
    }
}
