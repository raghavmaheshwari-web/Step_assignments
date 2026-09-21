package week7;

/**
 * Problem 3: Fleet Maintenance Tracker
 *
 * Abstract class ServiceableVehicle encapsulating mileage with validation.
 */
public abstract class ServiceableVehicle {
    private double mileage;

    public ServiceableVehicle() {
        this.mileage = 0.0;
    }

    public double getMileage() {
        return mileage;
    }

    /**
     * Adds mileage after validating that distance is non-negative.
     *
     * @param km distance traveled
     * @throws IllegalArgumentException if km is negative
     */
    public void addMileage(double km) {
        if (km < 0) {
            throw new IllegalArgumentException("Cannot add negative mileage: " + km);
        }
        this.mileage += km;
    }

    /**
     * Abstract method requiring specialized machine maintenance procedures.
     *
     * @return description of completed maintenance
     */
    public abstract String performMaintenance();
}
