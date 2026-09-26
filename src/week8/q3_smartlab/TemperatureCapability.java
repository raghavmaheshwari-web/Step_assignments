package week8.q3_smartlab;

/**
 * Capability controlling temperature with range validation (16–30°C).
 */
public class TemperatureCapability implements Capability {
    private int temperature;

    public TemperatureCapability() {
        this.temperature = 24;
    }

    public TemperatureCapability(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public String getName() {
        return "Temperature";
    }

    public int getTemperature() {
        return temperature;
    }

    public boolean setTemperature(Device device, int temp) {
        if (temp < 16 || temp > 30) {
            System.out.println("Rejected: " + device.getName() + " temperature must be between 16°C and 30°C.");
            return false;
        }
        this.temperature = temp;
        System.out.println(device.getName() + ": temperature set to " + temp + "°C.");
        return true;
    }
}
