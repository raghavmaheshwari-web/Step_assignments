package week8.q2_parcel;

/**
 * Enumeration of parcel lifecycle statuses with transition rules.
 */
public enum ParcelStatus {
    BOOKED,
    PICKED_UP,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED;

    public boolean canTransitionTo(ParcelStatus target) {
        if (this == BOOKED) {
            return target == PICKED_UP || target == CANCELLED;
        } else if (this == PICKED_UP) {
            return target == IN_TRANSIT;
        } else if (this == IN_TRANSIT) {
            return target == OUT_FOR_DELIVERY;
        } else if (this == OUT_FOR_DELIVERY) {
            return target == DELIVERED;
        }
        return false;
    }
}
