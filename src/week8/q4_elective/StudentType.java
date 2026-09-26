package week8.q4_elective;

/**
 * Standard university student categories and their semester credit limits.
 */
public enum StudentType implements CreditPolicy {
    REGULAR("Regular", 24),
    HONORS("Honors", 28),
    EXCHANGE("Exchange", 20);

    private final String name;
    private final int maxCredits;

    StudentType(String name, int maxCredits) {
        this.name = name;
        this.maxCredits = maxCredits;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxCredits() {
        return maxCredits;
    }
}
