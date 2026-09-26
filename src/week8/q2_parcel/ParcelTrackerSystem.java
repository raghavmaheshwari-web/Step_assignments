package week8.q2_parcel;

/**
 * Runner/Demo for Question 2: The SwiftShip Parcel Tracker
 */
public class ParcelTrackerSystem {
    public static void runDemo() {
        System.out.println("--- Question 2: The SwiftShip Parcel Tracker ---");
        ParcelService service = new ParcelService();
        Customer customer = new Customer("Rahul Sharma", "9876543210", "rahul@example.com");

        // 1. Customer books Express parcel 'P101' weighing 2 kg and subscribes to SMS and Email updates.
        Parcel p101 = service.bookParcel("P101", customer, 2.0, new ExpressShipping(),
                new SmsChannel(), new EmailChannel());

        // 2. Courier marks 'P101' as picked up.
        service.updateStatus(p101, ParcelStatus.PICKED_UP);

        // 3. Customer attempts to cancel 'P101'.
        service.cancelParcel(p101);

        // 4. Courier marks 'P101' as in transit.
        service.updateStatus(p101, ParcelStatus.IN_TRANSIT);

        // 5. Courier attempts to mark 'P101' as delivered.
        service.updateStatus(p101, ParcelStatus.DELIVERED);
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo();
    }
}
