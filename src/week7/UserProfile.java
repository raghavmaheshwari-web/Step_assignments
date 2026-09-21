package week7;

/**
 * UserProfile implements Exportable directly with no shared hierarchy with ReportGenerator.
 */
public class UserProfile implements Exportable {
    private final String username;

    public UserProfile(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("username cannot be null or blank.");
        }
        this.username = username.trim();
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String exportData() {
        ExportManager.incrementExportCount();
        return "Exported profile: " + username;
    }
}
