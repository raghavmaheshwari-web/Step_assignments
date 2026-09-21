package week7;

/**
 * Problem 5: Connected Home Control Panel
 *
 * Abstract base class HomeDevice assigning an immutable unique serialNumber
 * from a shared static counter starting at HD-1001.
 */
public abstract class HomeDevice {
    private static int deviceCounter = 1000;
    private final String serialNumber;

    public HomeDevice() {
        deviceCounter++;
        this.serialNumber = "HD-" + deviceCounter;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public static int getDeviceCounter() {
        return deviceCounter;
    }

    public static void resetDeviceCounter() {
        deviceCounter = 1000;
    }

    public abstract String activate();
}
