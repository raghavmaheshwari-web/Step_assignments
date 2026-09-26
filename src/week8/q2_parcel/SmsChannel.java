package week8.q2_parcel;

/**
 * SMS Notification Channel.
 */
public class SmsChannel implements NotificationChannel {
    @Override
    public void notify(String parcelId, ParcelStatus status) {
        System.out.println("[SMS] " + parcelId + " is now " + status + ".");
    }
}
