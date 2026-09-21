package week7;

/**
 * HeavyDutyForklift demonstrates a 3-level multilevel inheritance hierarchy:
 * ServiceableVehicle -> Forklift -> HeavyDutyForklift.
 * Overrides performMaintenance() by chaining to super and appending high-pressure checks.
 */
public class HeavyDutyForklift extends Forklift {

    public HeavyDutyForklift(String assetTag) {
        super(assetTag);
    }

    @Override
    public String performMaintenance() {
        return super.performMaintenance() + " | high-pressure hydraulic check complete";
    }
}
