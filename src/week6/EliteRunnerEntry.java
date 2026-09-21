package week6;

/**
 * EliteRunnerEntry demonstrates multilevel inheritance (RaceEntry -> RunnerEntry -> EliteRunnerEntry).
 * Models an elite athlete who receives a sponsor bonus.
 */
public class EliteRunnerEntry extends RunnerEntry {
    private final double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = Math.max(0.0, sponsorBonus);
    }

    public double getSponsorBonus() {
        return sponsorBonus;
    }

    @Override
    public String announce() {
        return "Elite Runner | Bib: " + bibNumber + " | Category: " + getCategory() +
               " | Sponsor Bonus: " + sponsorBonus + " | Balance: " + getBalanceDue();
    }
}
