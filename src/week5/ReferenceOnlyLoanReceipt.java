package week5;

/**
 * Problem 5: Reference-Only Loan Receipt
 *
 * Extends LoanReceipt to represent in-library reading room usage, adding a roomNumber field.
 */
public class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = (roomNumber != null) ? roomNumber.trim() : "Unassigned Room";
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
