package week7;

/**
 * Forklift extends ServiceableVehicle and implements Insurable.
 */
public class Forklift extends ServiceableVehicle implements Insurable {
    protected final String assetTag;

    public Forklift(String assetTag) {
        super();
        this.assetTag = (assetTag != null) ? assetTag.trim() : "Unknown-Asset";
    }

    public String getAssetTag() {
        return assetTag;
    }

    @Override
    public String performMaintenance() {
        return "Forklift " + assetTag + ": hydraulic and fork inspection complete";
    }

    @Override
    public String getInsuranceInfo() {
        return "Insured under fleet policy - Asset " + assetTag;
    }
}
