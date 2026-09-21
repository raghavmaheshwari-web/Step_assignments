package week5;

/**
 * Problem 3: Book Copy Circulation Guard
 *
 * Demonstrates strict encapsulation of state with private fields and validated
 * boundary transitions ensuring the invariant 0 <= copiesAvailable <= copiesTotal.
 */
public class BookInventory {
    private final int copiesTotal;
    private int copiesAvailable;

    /**
     * Constructs inventory with a positive total copy count.
     *
     * @param copiesTotal total number of copies
     * @throws IllegalArgumentException if copiesTotal is zero or negative
     */
    public BookInventory(int copiesTotal) {
        if (copiesTotal <= 0) {
            throw new IllegalArgumentException("Invalid inventory size: copiesTotal must be greater than zero.");
        }
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public int getCopiesTotal() {
        return copiesTotal;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    /**
     * Checks out a copy. Silently rejects if no copies are available,
     * ensuring count never drops below 0.
     */
    public void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    /**
     * Checks in a returned copy. Silently rejects if all copies are already returned,
     * ensuring count never exceeds copiesTotal.
     */
    public void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }
}
