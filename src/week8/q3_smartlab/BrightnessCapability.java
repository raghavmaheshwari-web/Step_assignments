package week8.q3_smartlab;

/**
 * Capability controlling light brightness with range validation (0–100%).
 */
public class BrightnessCapability implements Capability {
    private int brightness;

    public BrightnessCapability() {
        this.brightness = 0;
    }

    public BrightnessCapability(int brightness) {
        this.brightness = brightness;
    }

    @Override
    public String getName() {
        return "Brightness";
    }

    public int getBrightness() {
        return brightness;
    }

    public boolean setBrightness(Device device, int level) {
        if (level < 0 || level > 100) {
            System.out.println("Rejected: " + device.getName() + " brightness must be between 0% and 100%.");
            return false;
        }
        this.brightness = level;
        System.out.println(device.getName() + ": brightness set to " + level + "%.");
        return true;
    }
}
