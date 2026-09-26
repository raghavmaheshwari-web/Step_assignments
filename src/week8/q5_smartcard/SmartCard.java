package week8.q5_smartcard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * SmartCard with transactional ledger invariant (balance = sum of all transactions),
 * plan-based discounting, and one-time refund protection.
 */
public class SmartCard {
    private final String cardId;
    private PricingPlan plan;
    private CardStatus status;
    private final List<Transaction> transactions = new ArrayList<>();

    private static final double MIN_TOPUP = 100.0;
    private static final double MAX_BALANCE = 5000.0;

    public SmartCard(String cardId, PricingPlan plan) {
        if (cardId == null || cardId.trim().isEmpty()) {
            throw new IllegalArgumentException("Card ID cannot be empty.");
        }
        this.cardId = cardId.trim();
        this.plan = Objects.requireNonNull(plan, "PricingPlan cannot be null.");
        this.status = CardStatus.ACTIVE;
    }

    public String getCardId() {
        return cardId;
    }

    public PricingPlan getPlan() {
        return plan;
    }

    public void setPlan(PricingPlan plan) {
        this.plan = Objects.requireNonNull(plan);
    }

    public CardStatus getStatus() {
        return status;
    }

    public void block() {
        this.status = CardStatus.BLOCKED;
    }

    public void unblock() {
        this.status = CardStatus.ACTIVE;
    }

    /**
     * Ledger Invariant: balance is always dynamically derived as the sum
     * of all recorded transactions.
     */
    public double getBalance() {
        double total = 0.0;
        for (Transaction tx : transactions) {
            total += tx.getSignedAmount();
        }
        return total;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public boolean topUp(double amount) {
        if (status == CardStatus.BLOCKED) {
            System.out.printf("Top-up failed: Card %s is BLOCKED.\n", cardId);
            return false;
        }

        if (amount < MIN_TOPUP) {
            System.out.printf(Locale.US, "Top-up failed: Minimum top-up is ₹%.2f.\n", MIN_TOPUP);
            return false;
        }

        if (getBalance() + amount > MAX_BALANCE) {
            System.out.printf(Locale.US, "Top-up failed: Exceeds maximum balance of ₹%.2f.\n", MAX_BALANCE);
            return false;
        }

        Transaction tx = new Transaction(TransactionType.TOP_UP, amount, "Top-up");
        transactions.add(tx);
        System.out.printf(Locale.US, "%s topped up with ₹%.2f. Balance: ₹%.2f.\n",
                cardId, amount, getBalance());
        return true;
    }

    public boolean purchase(String itemName, double basePrice) {
        if (status == CardStatus.BLOCKED) {
            System.out.printf("Purchase failed: Card %s is BLOCKED.\n", cardId);
            return false;
        }

        double payable = plan.computePrice(basePrice);
        double currentBalance = getBalance();

        if (currentBalance < payable) {
            System.out.printf(Locale.US, "Purchase failed: Insufficient balance (required ₹%.2f, available ₹%.2f).\n",
                    payable, currentBalance);
            return false;
        }

        Transaction tx = new Transaction(TransactionType.PURCHASE, payable, itemName);
        transactions.add(tx);
        System.out.printf(Locale.US, "%s purchased for ₹%.2f. Balance: ₹%.2f.\n",
                itemName, payable, getBalance());
        return true;
    }

    public boolean refund(String itemName) {
        Transaction targetPurchase = null;
        // Locate the latest purchase of this item
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction tx = transactions.get(i);
            if (tx.getType() == TransactionType.PURCHASE && tx.getDescription().equalsIgnoreCase(itemName)) {
                targetPurchase = tx;
                break;
            }
        }

        if (targetPurchase == null) {
            System.out.printf("Refund rejected: No purchase found for %s.\n", itemName);
            return false;
        }

        if (targetPurchase.isRefunded()) {
            System.out.printf("Refund rejected: %s has already been refunded.\n", itemName);
            return false;
        }

        targetPurchase.setRefunded(true);
        Transaction refundTx = new Transaction(TransactionType.REFUND, targetPurchase.getAmount(), itemName);
        transactions.add(refundTx);

        System.out.printf(Locale.US, "Refund of ₹%.2f for %s processed. Balance: ₹%.2f.\n",
                targetPurchase.getAmount(), itemName, getBalance());
        return true;
    }

    public void printMiniStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mini-statement for ").append(cardId).append(": ");
        for (int i = 0; i < transactions.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(transactions.get(i).formatForStatement());
        }
        sb.append(String.format(Locale.US, " = ₹%.2f.", getBalance()));
        System.out.println(sb.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartCard smartCard = (SmartCard) o;
        return Objects.equals(cardId, smartCard.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "SmartCard[%s, Plan=%s, Balance=₹%.2f, Status=%s]",
                cardId, plan.getName(), getBalance(), status);
    }
}
