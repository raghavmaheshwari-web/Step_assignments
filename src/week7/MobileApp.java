package week7;

/**
 * MobileApp implements RemoteControllable with no relationship to HomeDevice.
 */
public class MobileApp implements RemoteControllable {
    private final String appName;

    public MobileApp(String appName) {
        this.appName = (appName != null) ? appName.trim() : "Mobile App";
    }

    public String getAppName() {
        return appName;
    }

    @Override
    public String connect(String appId) {
        return appName + " connected to " + appId;
    }
}
