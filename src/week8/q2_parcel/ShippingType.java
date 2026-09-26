package week8.q2_parcel;

/**
 * Strategy interface for shipping charge calculation.
 */
public interface ShippingType {
    String getName();
    double calculateCharge(double weightKg);
}
