package week3;

/**
 * ParkingSlot model representing an individual parking spot and its capacity.
 */
public class ParkingSlot {
    private String slotNo;
    private int capacity;
    private int occupiedCount;

    public ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = Math.max(1, capacity);
        this.occupiedCount = Math.max(0, Math.min(occupiedCount, capacity));
    }

    public String getSlotNo() {
        return slotNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupiedCount() {
        return occupiedCount;
    }

    public boolean isAvailable() {
        return occupiedCount < capacity;
    }

    /**
     * Allots a spot to a vehicle if capacity permits.
     *
     * @param vehicleNo vehicle license plate number
     * @return true if successfully allotted, false if already full
     */
    public boolean allot(String vehicleNo) {
        if (isAvailable()) {
            occupiedCount++;
            return true;
        }
        return false;
    }
}
