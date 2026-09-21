package week3;

/**
 * Problem F3: Object References, Null Safety, and a Mutating Method
 *
 * Demonstrates:
 * 1. Java pass-by-value semantics for object references (why arrays and objects are not copied).
 * 2. Defensive programming and null-safety patterns that eliminate NullPointerException risks.
 */
public class ParkingSlotAllocationSystem {

    /**
     * Explanation of Object References vs Copies:
     * In Java, variables holding objects (or arrays of objects) do not hold the object's
     * raw data directly; they store a reference (memory address) pointing to the object on the heap.
     * When a ParkingSlot[] array is passed as an argument to findAvailableSlot() or safeAllot(),
     * Java copies the reference to the array (pass-by-value of references).
     *
     * The slots within that array are NOT cloned. Both the caller and the method refer to
     * the exact same ParkingSlot instances in heap memory. Consequently, calling slot.allot()
     * directly mutates the state of the original object, and the change is immediately
     * reflected everywhere that reference is accessed.
     */

    /**
     * Finds the first available parking slot with occupiedCount < capacity.
     *
     * @param slots array of ParkingSlot objects
     * @return the first available ParkingSlot, or null if all slots are full or array is null/empty
     */
    public static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        if (slots == null) {
            return null;
        }
        for (ParkingSlot slot : slots) {
            if (slot != null && slot.isAvailable()) {
                return slot;
            }
        }
        return null;
    }

    /**
     * Null-safe allotment method that guarantees zero risk of NullPointerException.
     *
     * @param slots array of ParkingSlot objects
     * @param vehicleNo registration number of the vehicle
     */
    public static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        if (vehicleNo == null || vehicleNo.trim().isEmpty()) {
            System.out.println("Invalid vehicle number.");
            return;
        }

        // Find available slot with null-safety check
        ParkingSlot availableSlot = findAvailableSlot(slots);

        if (availableSlot != null) {
            boolean success = availableSlot.allot(vehicleNo);
            if (success) {
                System.out.println(vehicleNo + " allotted to slot " + availableSlot.getSlotNo());
            } else {
                System.out.println("No slots available for " + vehicleNo);
            }
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }

    public static void runDemo() {
        System.out.println("--- Scenario 1: Slot available (A1 has 3/4 occupied, A2 has 5/5) ---");
        ParkingSlot[] availableSlots = {
            new ParkingSlot("A1", 4, 3),
            new ParkingSlot("A2", 5, 5)
        };
        safeAllot(availableSlots, "TN09AB1234");

        System.out.println("\n--- Scenario 2: All slots full (A1 has 4/4 occupied, A2 has 5/5) ---");
        ParkingSlot[] fullSlots = {
            new ParkingSlot("A1", 4, 4),
            new ParkingSlot("A2", 5, 5)
        };
        safeAllot(fullSlots, "TN09AB1234");
    }

    public static void main(String[] args) {
        runDemo();
    }
}
