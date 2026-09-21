package week4;

/**
 * Problem 5: Nightly Multi-Kitchen Reconciliation Engine
 *
 * Demonstrates:
 * 1. Safe multi-account batch processing without crashing on null records.
 * 2. Dynamic instanceof dispatch between regular and premium accounts.
 * 3. Operations justification for parallel array length integrity.
 */
public class NightlyReconciliationEngine {

    /*
     * Operations Manager Justification for Parallel Array Integrity:
     * When processing financial transactions across parallel arrays (accounts, amounts, delayMinutesArray),
     * a length mismatch indicates severe data corruption upstream. Processing a mismatched batch would
     * misalign indices and charge one student's food amount or delay penalty to an entirely different
     * student's account. Therefore, failing fast by throwing an IllegalArgumentException before any
     * transaction is executed is mandatory to prevent irreversible accounting errors.
     */

    /**
     * Processes a single account with null safety and instanceof dispatch.
     *
     * @param account the delivery account (or null)
     * @param amount monetary order amount
     * @param delayMinutes delay minutes past schedule
     * @return surge fee charged, or 0.0 if account is null
     */
    public double processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null) {
            return 0.0;
        }

        if (account instanceof PremiumDeliveryAccount) {
            PremiumDeliveryAccount premium = (PremiumDeliveryAccount) account;
            return premium.calculatePremiumSurgeFee(delayMinutes);
        } else {
            return account.calculateSurgeFee(delayMinutes);
        }
    }

    /**
     * Reconciles a batch of accounts across parallel arrays in a single pass with O(1) extra space.
     *
     * @param accounts array of DeliveryAccount objects (may contain nulls)
     * @param amounts array of order amounts
     * @param delayMinutesArray array of delay minutes
     */
    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        if (accounts == null || amounts == null || delayMinutesArray == null) {
            throw new IllegalArgumentException("Input arrays cannot be null.");
        }

        if (accounts.length != amounts.length || accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException("Parallel array length mismatch: accounts=" + accounts.length +
                    ", amounts=" + amounts.length + ", delayMinutes=" + delayMinutesArray.length);
        }

        int processedCount = 0;
        int nullSkippedCount = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotalSurgeFees = 0.0;

        NightlyReconciliationEngine engine = new NightlyReconciliationEngine();

        for (int i = 0; i < accounts.length; i++) {
            DeliveryAccount acc = accounts[i];
            double amt = amounts[i];
            int delay = delayMinutesArray[i];

            if (acc == null) {
                nullSkippedCount++;
                continue;
            }

            processedCount++;
            if (acc instanceof PremiumDeliveryAccount) {
                premiumCount++;
            } else {
                regularCount++;
            }

            double fee = engine.processAccount(acc, amt, delay);
            grandTotalSurgeFees += fee;
        }

        System.out.println(processedCount + " processed | " +
                nullSkippedCount + " null skipped | " +
                premiumCount + " premium | " +
                regularCount + " regular | " +
                "grand total surge fees = Rs " + String.format("%.2f", grandTotalSurgeFees));
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new PremiumDeliveryAccount("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };
        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};

        processBatch(accounts, amounts, delayMinutesArray);
    }
}
