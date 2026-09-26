package week8.q5_smartcard;

import java.util.Locale;

/**
 * Immutable transaction record maintained in the card ledger.
 */
public class Transaction {
    private final TransactionType type;
    private final double amount;
    private final String description;
    private boolean refunded;

    public Transaction(TransactionType type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.refunded = false;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }

    public double getSignedAmount() {
        switch (type) {
            case TOP_UP:
            case REFUND:
                return amount;
            case PURCHASE:
                return -amount;
            default:
                return 0.0;
        }
    }

    public String formatForStatement() {
        if (type == TransactionType.PURCHASE) {
            return String.format(Locale.US, "-%.2f", amount);
        } else {
            return String.format(Locale.US, "+%.2f", amount);
        }
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s: %s (₹%.2f)", type, description, amount);
    }
}
