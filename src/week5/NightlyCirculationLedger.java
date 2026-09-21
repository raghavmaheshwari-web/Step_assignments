package week5;

/**
 * Problem 5: Nightly Circulation Ledger
 *
 * Demonstrates:
 * 1. Static initialization block for class-level registry setup.
 * 2. Null-safe batch processing across receipt hierarchies using instanceof.
 */
public class NightlyCirculationLedger {
    private static final String LEDGER_VERSION;
    private static boolean isInitialized;

    // Static initialization block setting up one-time shared state
    static {
        LEDGER_VERSION = "2.0";
        isInitialized = true;
    }

    public static String getLedgerVersion() {
        return LEDGER_VERSION;
    }

    /**
     * Processes a batch of loan receipts, safely skipping nulls and distinguishing
     * ReferenceOnlyLoanReceipt from regular LoanReceipt objects.
     *
     * @param receipts array of LoanReceipt instances (may contain nulls)
     * @return summary string
     */
    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               referenceOnly + " reference-only | " +
               regular + " regular";
    }

    public static void main(String[] args) {
        LoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };

        System.out.println(processNightlyCirculation(receipts));
    }
}
