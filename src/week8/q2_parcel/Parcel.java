package week8.q2_parcel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Parcel class managing state transitions, shipping charge calculation,
 * and observer notifications.
 */
public class Parcel {
    private final String trackingId;
    private final Customer customer;
    private final double weightKg;
    private final ShippingType shippingType;
    private ParcelStatus status;
    private final List<NotificationChannel> channels = new ArrayList<>();

    public Parcel(String trackingId, Customer customer, double weightKg, ShippingType shippingType) {
        this.trackingId = trackingId;
        this.customer = customer;
        this.weightKg = weightKg;
        this.shippingType = shippingType;
        this.status = ParcelStatus.BOOKED;
    }

    public void subscribeChannel(NotificationChannel channel) {
        if (channel != null && !channels.contains(channel)) {
            channels.add(channel);
        }
    }

    public void unsubscribeChannel(NotificationChannel channel) {
        channels.remove(channel);
    }

    public void notifyChannels() {
        for (NotificationChannel channel : channels) {
            channel.notify(trackingId, status);
        }
    }

    public double calculateCharge() {
        return shippingType.calculateCharge(weightKg);
    }

    public boolean updateStatus(ParcelStatus newStatus) {
        if (!status.canTransitionTo(newStatus)) {
            System.out.printf("Invalid transition: %s → %s is not allowed.\n", status, newStatus);
            return false;
        }
        this.status = newStatus;
        notifyChannels();
        return true;
    }

    public boolean cancel() {
        if (status != ParcelStatus.BOOKED) {
            System.out.printf("Cancellation failed: %s can be cancelled only while BOOKED.\n", trackingId);
            return false;
        }
        this.status = ParcelStatus.CANCELLED;
        notifyChannels();
        return true;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public ShippingType getShippingType() {
        return shippingType;
    }

    public ParcelStatus getStatus() {
        return status;
    }

    public List<NotificationChannel> getChannels() {
        return Collections.unmodifiableList(channels);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return Objects.equals(trackingId, parcel.trackingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackingId);
    }

    @Override
    public String toString() {
        return trackingId + " [" + status + "]";
    }
}
