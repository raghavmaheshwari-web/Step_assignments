package week7;

/**
 * Refrigerator is a sibling of WashingMachine under HomeDevice.
 * Implements only EnergyTrackable, demonstrating that siblings can adopt different capabilities.
 */
public class Refrigerator extends HomeDevice implements EnergyTrackable {
    private final double consumptionWatts;

    public Refrigerator(double consumptionWatts) {
        super();
        this.consumptionWatts = Math.max(0.0, consumptionWatts);
    }

    @Override
    public String activate() {
        return "Refrigerator " + getSerialNumber() + " cooling cycle started";
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}
