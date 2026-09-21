package week5;

/**
 * Problem 4: LibraryMember JavaBean, Chained Constructors & Security Answer
 *
 * Implements:
 * 1. JavaBean specification with 3 chained constructors via this(...).
 * 2. Write-once membershipId property (silently ignores subsequent modifications).
 * 3. Standard JavaBean boolean property naming: isPremiumMember() / setPremiumMember().
 * 4. True write-only securityAnswer property with no getter anywhere.
 */
public class LibraryMemberBean {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash; // Write-only field

    private boolean idSetOnce = false; // Guard for write-once constraint

    /**
     * No-argument constructor required by JavaBean frameworks.
     * Chains to the full constructor via this(...).
     */
    public LibraryMemberBean() {
        this(null, null);
    }

    /**
     * Name-only constructor for partial signup.
     * Chains to the full constructor via this(...).
     */
    public LibraryMemberBean(String name) {
        this(null, name);
    }

    /**
     * Primary constructor receiving membershipId and name.
     */
    public LibraryMemberBean(String membershipId, String name) {
        this.name = name;
        if (membershipId != null && !membershipId.trim().isEmpty()) {
            this.membershipId = membershipId.trim();
            this.idSetOnce = true;
        } else {
            this.membershipId = null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembershipId() {
        return membershipId;
    }

    /**
     * Write-once setter for membershipId.
     * Subsequent invocations after the ID has been set are silently ignored.
     */
    public void setMembershipId(String id) {
        if (!idSetOnce && id != null && !id.trim().isEmpty()) {
            this.membershipId = id.trim();
            this.idSetOnce = true;
        }
        // Silently ignored if already set once
    }

    /**
     * JavaBean boolean accessor following standard 'isX()' convention.
     */
    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    /**
     * Write-only property: stores a one-way deterministic hash of the answer.
     * Absolutely no getter exists anywhere in the class.
     */
    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            this.securityAnswerHash = Integer.toHexString(answer.trim().toLowerCase().hashCode());
        }
    }
}
