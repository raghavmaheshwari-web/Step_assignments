package week7;

/**
 * FleetTracker provides utility methods to safely inspect vehicle insurance capabilities.
 */
public class FleetTracker {

    /**
     * Checks whether a vehicle implements Insurable using instanceof,
     * safely casting to retrieve the policy details if applicable.
     *
     * @param v vehicle instance
     * @return insurance description, or message indicating no insurance record exists
     */
    public static String getInsuranceIfApplicable(ServiceableVehicle v) {
        if (v instanceof Insurable) {
            Insurable insurableVehicle = (Insurable) v;
            return insurableVehicle.getInsuranceInfo();
        }
        return "No insurance record exists";
    }
}
