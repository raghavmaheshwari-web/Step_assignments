package week4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Problem 2: ASAP or Scheduled — Delivery Slot Booking
 *
 * Demonstrates constructor overloading and this(...) constructor chaining.
 * Defaults unassigned slots to "ASAP" without code duplication, and classifies
 * peak hour slots.
 */
public class DeliverySlot {
    public static final String DEFAULT_SLOT = "ASAP";

    private static final Set<String> PEAK_HOURS = new HashSet<>(Arrays.asList(
            "12:00-13:00", "13:00-14:00", "19:00-20:00", "20:00-21:00"
    ));

    private final String orderId;
    private final String timeSlot;

    /**
     * Primary parameterized constructor setting both orderId and timeSlot.
     */
    public DeliverySlot(String orderId, String timeSlot) {
        this.orderId = (orderId != null) ? orderId.trim() : "";
        this.timeSlot = (timeSlot != null) ? timeSlot.trim() : DEFAULT_SLOT;
    }

    /**
     * Overloaded constructor for ASAP orders, chaining to the primary constructor via this(...).
     * "ASAP" is referenced via the constant DEFAULT_SLOT and typed only once in the class.
     */
    public DeliverySlot(String orderId) {
        this(orderId, DEFAULT_SLOT);
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    /**
     * Checks whether the booked slot falls into a peak hour.
     * Peak slots: "12:00-13:00", "13:00-14:00", "19:00-20:00", "20:00-21:00".
     *
     * @return true if peak hour slot, false otherwise
     */
    public boolean isPeakHour() {
        return PEAK_HOURS.contains(this.timeSlot);
    }

    public static void main(String[] args) {
        DeliverySlot slot1 = new DeliverySlot("ORD101", "13:00-14:00");
        System.out.println("ORD101 (13:00-14:00) isPeakHour: " + slot1.isPeakHour());

        DeliverySlot slot2 = new DeliverySlot("ORD102");
        System.out.println("ORD102 (default " + slot2.getTimeSlot() + ") isPeakHour: " + slot2.isPeakHour());
    }
}
