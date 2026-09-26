package week8.q3_smartlab;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Smart device using composition to hold dynamic capabilities.
 */
public class Device {
    private final String name;
    private final Map<Class<? extends Capability>, Capability> capabilities = new LinkedHashMap<>();

    public Device(String name, Capability... initialCapabilities) {
        this.name = name;
        for (Capability cap : initialCapabilities) {
            capabilities.put(cap.getClass(), cap);
        }
    }

    public String getName() {
        return name;
    }

    public void addCapability(Capability cap) {
        capabilities.put(cap.getClass(), cap);
        System.out.println(name + ": " + cap.getName() + " capability added.");
    }

    public boolean hasCapability(Class<? extends Capability> capClass) {
        return capabilities.containsKey(capClass);
    }

    @SuppressWarnings("unchecked")
    public <T extends Capability> T getCapability(Class<T> capClass) {
        return (T) capabilities.get(capClass);
    }

    public boolean setPower(boolean state) {
        PowerCapability cap = getCapability(PowerCapability.class);
        if (cap != null) {
            return cap.setPower(this, state);
        }
        return false;
    }

    public boolean setBrightness(int level) {
        BrightnessCapability cap = getCapability(BrightnessCapability.class);
        if (cap != null) {
            return cap.setBrightness(this, level);
        }
        return false;
    }

    public boolean setTemperature(int temp) {
        TemperatureCapability cap = getCapability(TemperatureCapability.class);
        if (cap != null) {
            return cap.setTemperature(this, temp);
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(name, device.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + " " + capabilities.keySet();
    }
}
