package week8.q2_parcel;

/**
 * Email Notification Channel.
 */
public class EmailChannel implements NotificationChannel {
    @Override
    public void notify(String parcelId, ParcelStatus status) {
        System.out.println("[Email] " + parcelId + " is now " + status + ".");
    }
}
