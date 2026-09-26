package week8.q3_smartlab;

/**
 * Capability controlling binary power state (ON / OFF).
 */
public class PowerCapability implements Capability {
    private boolean on;

    @Override
    public String getName() {
        return "Power";
    }

    public boolean isOn() {
        return on;
    }

    public boolean setPower(Device device, boolean state) {
        this.on = state;
        System.out.println(device.getName() + ": " + (state ? "ON." : "OFF."));
        return true;
    }
}
