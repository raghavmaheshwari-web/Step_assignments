package week7;

/**
 * SmartHomeController coordinates device connections and inspects energy trackability.
 */
public class SmartHomeController {

    /**
     * Loops through an array of RemoteControllable items and connects each polymorphically.
     *
     * @param items array of RemoteControllable instances
     * @param appId target application identifier
     */
    public static void connectAll(RemoteControllable[] items, String appId) {
        if (items == null) {
            return;
        }

        for (RemoteControllable item : items) {
            if (item != null) {
                System.out.println(item.connect(appId));
            }
        }
    }

    /**
     * Accepts a generic HomeDevice reference and inspects whether it implements EnergyTrackable
     * using instanceof, safely casting to read its consumption in watts.
     *
     * @param d HomeDevice reference
     * @return energy consumption in watts, or 0.0 if not trackable
     */
    public static double getConsumptionIfTrackable(HomeDevice d) {
        if (d instanceof EnergyTrackable) {
            EnergyTrackable trackable = (EnergyTrackable) d;
            return trackable.getConsumptionWatts();
        }
        return 0.0;
    }
}
