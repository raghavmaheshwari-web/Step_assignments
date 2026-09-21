package week6;

import java.util.Arrays;

/**
 * Base class for marathon entries: Riverside City Marathon.
 *
 * Demonstrates:
 * 1. Construction-time validation and final auto-incrementing entry codes.
 * 2. Overloaded payment processing (flat and mode-aware).
 * 3. Encapsulated mutable late-fee audit history with defensive copying.
 * 4. Character-by-character string format validation without regex.
 * 5. Polymorphic foundation for race family inheritance hierarchies.
 */
public class RaceEntry {
    private static int bibCounter = 0;

    protected final String bibNumber;
    protected final double entryFee;
    protected double amountPaid;
    private final String entryCode;

    private final double[] lateFeeHistory;
    private int lateFeeCount;

    /**
     * Parameterized constructor validating bibNumber and entryFee.
     * Increments the static bibCounter and assigns a final entryCode only on success.
     *
     * @param bibNumber marathon bib number (min 4 characters, non-blank)
     * @param entryFee base entry fee
     * @throws IllegalArgumentException if bibNumber is invalid or entryFee is negative
     */
    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bibNumber: must not be blank and must be at least 4 characters long.");
        }
        if (entryFee < 0) {
            throw new IllegalArgumentException("entryFee cannot be negative.");
        }

        this.bibNumber = bibNumber.trim();
        this.entryFee = entryFee;
        this.amountPaid = 0.0;
        this.lateFeeHistory = new double[10];
        this.lateFeeCount = 0;

        // Auto-increment static counter and assign immutable entry code
        bibCounter++;
        this.entryCode = "ENTRY-" + bibCounter;
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    public static void resetBibCounter() {
        bibCounter = 0;
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public String getEntryCode() {
        return entryCode;
    }

    /**
     * Flat amount payment toward the entry fee.
     *
     * @param amount payment amount
     */
    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        }
    }

    /**
     * Mode-aware payment that logs the payment channel and reuses pay(amount).
     *
     * @param amount payment amount
     * @param mode payment method (e.g. "UPI", "Credit Card")
     */
    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    /**
     * Records a late fee penalty into the entry's private audit trail.
     *
     * @param amount penalty amount
     */
    protected void applyLateFee(double amount) {
        if (amount > 0 && lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount++] = amount;
        }
    }

    /**
     * Returns a defensive copy of the late-fee audit trail to prevent external tampering.
     *
     * @return cloned array of recorded late penalties
     */
    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }

    /**
     * Computes the total balance due (entry fee + total late penalties - amount paid).
     *
     * @return balance due
     */
    public double getBalanceDue() {
        double totalLateFees = 0.0;
        for (int i = 0; i < lateFeeCount; i++) {
            totalLateFees += lateFeeHistory[i];
        }
        return (entryFee + totalLateFees) - amountPaid;
    }

    /**
     * Polymorphic announcement method describing the race entry.
     *
     * @return formatted announcement string
     */
    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
    }

    /**
     * Batch registration validator attempting to construct entries without pre-validation.
     *
     * @param bibNumbers array of candidate bib numbers
     * @param entryFee entry fee for each registration
     * @return summary string "Registered: X | Rejected: Y"
     */
    public static String registerBatch(String[] bibNumbers, double entryFee) {
        if (bibNumbers == null) {
            return "Registered: 0 | Rejected: 0";
        }

        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    /**
     * Validates discount code format: "M" + 3 digits + 1 uppercase letter (e.g. "M123A")
     * using charAt() and character inspection methods without regular expressions.
     *
     * @param code candidate discount code
     * @return true if valid format, false otherwise
     */
    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }

        // Must start with letter 'M'
        if (code.charAt(0) != 'M') {
            return false;
        }

        // Characters 1, 2, 3 must be digits
        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return false;
            }
        }

        // Character 4 must be an uppercase letter
        return Character.isUpperCase(code.charAt(4));
    }

    /**
     * Reconciles a nightly batch of mixed race entries tolerating null entries.
     *
     * @param entries array of RaceEntry objects (may include nulls)
     * @return formatted reconciliation summary
     */
    public static String settleNight(RaceEntry[] entries) {
        if (entries == null) {
            return "0 processed | 0 null skipped | 0 relay | 0 individual";
        }

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry entry : entries) {
            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (entry instanceof RelayTeamEntry) {
                relay++;
            } else {
                individual++;
            }
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               relay + " relay | " +
               individual + " individual";
    }
}
