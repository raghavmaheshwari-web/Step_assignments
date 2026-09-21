package week6;

/**
 * Problems 2 & 4: The Race-Day Announcer Board & Classification Engine
 *
 * Demonstrates:
 * 1. Classification via instanceof type checking without manual type codes.
 * 2. Polymorphic total computation across mixed subclass arrays.
 * 3. Unified report generation using StringBuilder and instanceof-guarded downcasts.
 */
public class AnnouncerBoard {

    /**
     * Classifies the inheritance generation of a RaceEntry using instanceof checks.
     *
     * @param entry candidate race entry
     * @return description of the inheritance branch
     */
    public static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        } else if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        } else if (entry instanceof RunnerEntry) {
            return "Direct single descendant (2 generations deep)";
        } else if (entry != null) {
            return "Base entry (root generation)";
        }
        return "Null entry";
    }

    /**
     * Computes the total balance due across an array of any mixed RaceEntry subclasses
     * purely through polymorphic getBalanceDue() invocation without manual type checking.
     *
     * @param entries array of mixed RaceEntry objects
     * @return sum of balances due
     */
    public static double getTotalBalanceDue(RaceEntry[] entries) {
        if (entries == null) {
            return 0.0;
        }

        double total = 0.0;
        for (RaceEntry entry : entries) {
            if (entry != null) {
                total += entry.getBalanceDue();
            }
        }
        return total;
    }

    /**
     * Loops over entries, invoking announce() polymorphically and assembling the final report
     * into a single StringBuilder. Safely guards a downcast to RelayTeamEntry via instanceof.
     *
     * @param entries array of RaceEntry objects
     * @return assembled announcement report string
     */
    public static String announceAll(RaceEntry[] entries) {
        if (entries == null || entries.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (RaceEntry entry : entries) {
            if (entry == null) continue;

            // Polymorphic call to announce()
            sb.append(entry.announce());

            // Safe instanceof-guarded downcast for relay-specific detail
            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry relay = (RelayTeamEntry) entry;
                sb.append(" [Team size via downcast: ").append(relay.getTeamSize()).append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }
}
