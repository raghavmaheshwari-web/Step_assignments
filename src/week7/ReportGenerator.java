package week7;

/**
 * ReportGenerator implements Exportable directly with no shared hierarchy with UserProfile.
 */
public class ReportGenerator implements Exportable {
    private final String reportName;

    public ReportGenerator(String reportName) {
        if (reportName == null || reportName.trim().isEmpty()) {
            throw new IllegalArgumentException("reportName cannot be null or blank.");
        }
        this.reportName = reportName.trim();
    }

    public String getReportName() {
        return reportName;
    }

    @Override
    public String exportData() {
        ExportManager.incrementExportCount();
        return "Exported report: " + reportName;
    }
}
