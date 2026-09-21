package week6;

/**
 * RelayTeamEntry demonstrates hierarchical inheritance (directly extends RaceEntry).
 * Represents a relay team with an explicit team size.
 */
public class RelayTeamEntry extends RaceEntry {
    private final int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = Math.max(1, teamSize);
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + bibNumber + " | Team Size: " + teamSize +
               " | Balance: " + getBalanceDue();
    }
}
