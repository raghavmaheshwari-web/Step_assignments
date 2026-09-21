package week5;

/**
 * Problem 1: LibraryMember
 *
 * Demonstrates proper field-level access modifier selection and strict
 * construction-time validation for member identity.
 */
public class LibraryMember {
    private String membershipId;
    protected String branchCode;
    protected double finesOwed;
    public String displayName;

    /**
     * Parameterized constructor validating membershipId format.
     * Rejects null, blank, whitespace-only, or values shorter than 4 characters.
     *
     * @param membershipId unique member ID (min 4 characters)
     * @param branchCode branch identifier
     * @param finesOwed outstanding fines
     * @param displayName public display name
     * @throws IllegalArgumentException if membershipId is invalid
     */
    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid membershipId: must not be blank and must be at least 4 characters long.");
        }
        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = Math.max(0.0, finesOwed);
        this.displayName = displayName;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public double getFinesOwed() {
        return finesOwed;
    }
}
