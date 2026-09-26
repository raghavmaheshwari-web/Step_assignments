package week8.q2_parcel;

import java.util.Locale;

/**
 * Service managing parcel booking, courier tracking updates, and cancellation.
 */
public class ParcelService {

    public Parcel bookParcel(String trackingId, Customer customer, double weightKg,
                             ShippingType shippingType, NotificationChannel... channels) {
        Parcel parcel = new Parcel(trackingId, customer, weightKg, shippingType);
        for (NotificationChannel ch : channels) {
            parcel.subscribeChannel(ch);
        }

        String weightStr = (weightKg == (long) weightKg)
                ? String.format(Locale.US, "%d kg", (long) weightKg)
                : String.format(Locale.US, "%.1f kg", weightKg);

        System.out.printf(Locale.US, "Parcel %s booked (%s, %s). Charge: ₹%.2f.\n",
                trackingId, shippingType.getName(), weightStr, parcel.calculateCharge());

        parcel.notifyChannels();
        return parcel;
    }

    public boolean updateStatus(Parcel parcel, ParcelStatus status) {
        return parcel.updateStatus(status);
    }

    public boolean cancelParcel(Parcel parcel) {
        return parcel.cancel();
    }
}
