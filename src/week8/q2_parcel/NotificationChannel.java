package week8.q2_parcel;

/**
 * Observer interface for parcel status change notifications.
 */
public interface NotificationChannel {
    void notify(String parcelId, ParcelStatus status);
}
