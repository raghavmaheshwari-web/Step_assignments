package week7;

/**
 * Manages export operations and tracks a single shared static export counter
 * across all implementing classes.
 */
public class ExportManager {
    private static int totalExports = 0;

    public static int getTotalExports() {
        return totalExports;
    }

    public static void incrementExportCount() {
        totalExports++;
    }

    public static void resetExportCount() {
        totalExports = 0;
    }

    /**
     * Loops through an array of mixed Exportable items and calls exportData() on each.
     *
     * @param items array of Exportable references
     */
    public static void exportAll(Exportable[] items) {
        if (items == null) {
            return;
        }

        for (Exportable item : items) {
            if (item != null) {
                String message = item.exportData();
                System.out.println(message);
            }
        }
    }
}
