package week5;

import java.util.Arrays;

/**
 * Problem 5: Immutable Loan Receipt
 *
 * Implements strict immutability with:
 * 1. Final fields and defense against mutation.
 * 2. Construction-time validation enforcing "BK-XXX" book ID formats.
 * 3. Defensive copying on both entry (constructor) and exit (getter).
 * 4. Immutable modification via the "wither" pattern (withCorrectedBookId).
 */
public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    /**
     * Constructs an immutable LoanReceipt after validating memberId and all bookIds.
     *
     * @param memberId unique member identifier
     * @param bookIds array of book IDs matching "BK-" + 3 digits
     * @throws IllegalArgumentException if memberId is invalid or any bookId does not match format
     */
    public LoanReceipt(String memberId, String[] bookIds) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid memberId: cannot be null or empty.");
        }
        if (bookIds == null || bookIds.length == 0) {
            throw new IllegalArgumentException("bookIds array cannot be null or empty.");
        }

        // Validate each bookId format: must match "BK-" followed by exactly 3 digits
        for (int i = 0; i < bookIds.length; i++) {
            String id = bookIds[i];
            if (!isValidBookId(id)) {
                throw new IllegalArgumentException("Invalid bookId '" + id + "' at index " + i +
                        ": must match format 'BK-' followed by 3 digits (e.g., 'BK-100').");
            }
        }

        this.memberId = memberId.trim();
        // Defensive copy on entry
        this.bookIds = bookIds.clone();
    }

    private static boolean isValidBookId(String id) {
        if (id == null || id.length() != 6) {
            return false;
        }
        if (!id.startsWith("BK-")) {
            return false;
        }
        for (int i = 3; i < 6; i++) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String getMemberId() {
        return memberId;
    }

    /**
     * Defensive copy on exit: returns a clone to prevent external callers
     * from mutating the internal array state.
     */
    public String[] getBookIds() {
        return bookIds.clone();
    }

    /**
     * Wither method: returns a brand-new LoanReceipt with a corrected book ID at the given index.
     * Preserves the original receipt's immutability.
     */
    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (!isValidBookId(newId)) {
            throw new IllegalArgumentException("Invalid replacement bookId format: " + newId);
        }

        String[] updatedIds = bookIds.clone();
        updatedIds[index] = newId;
        return new LoanReceipt(this.memberId, updatedIds);
    }
}
