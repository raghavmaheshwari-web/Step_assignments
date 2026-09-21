package week6;

/**
 * RunnerEntry specializes RaceEntry via single inheritance.
 * Adds category management and overrides applyLateFee with doubled penalties.
 */
public class RunnerEntry extends RaceEntry {
    private final String category;

    /**
     * Parameterized constructor forwarding shared fields to super(...).
     *
     * @param bibNumber marathon bib number
     * @param entryFee base entry fee
     * @param category runner category (e.g. "Open 10K", "Full Marathon")
     */
    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = (category != null) ? category.trim() : "General";
    }

    public String getCategory() {
        return category;
    }

    /**
     * Overrides applyLateFee to impose a doubled penalty on late individual runners.
     * Reuses parent deduction and defensive audit trail recording via super.applyLateFee.
     */
    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2.0);
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + bibNumber + " | Category: " + category +
               " | Balance: " + getBalanceDue();
    }
}
