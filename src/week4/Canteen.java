package week4;

/**
 * Problem 3: Canteen Trust-Score Ranking Engine
 *
 * Demonstrates:
 * 1. Resolving field/parameter shadowing using 'this'.
 * 2. Constructor chaining with a default trust score of 3.
 * 3. Consistent, multi-tier comparison logic (score descending, case-insensitive code ascending).
 * 4. Custom sorting algorithm implementation without built-in libraries.
 */
public class Canteen {
    public static final int DEFAULT_TRUST_SCORE = 3;

    private String canteenCode;
    private String canteenName;
    private int trustScore;

    /**
     * Primary constructor using 'this' to resolve variable naming clashes.
     */
    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = (canteenCode != null) ? canteenCode.trim() : "";
        this.canteenName = (canteenName != null) ? canteenName.trim() : "";
        this.trustScore = trustScore;
    }

    /**
     * Overloaded constructor chaining via this(...) with default trust score of 3.
     */
    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, DEFAULT_TRUST_SCORE);
    }

    public String getCanteenCode() {
        return this.canteenCode;
    }

    public String getCanteenName() {
        return this.canteenName;
    }

    public int getTrustScore() {
        return this.trustScore;
    }

    /**
     * Compares this canteen against another following standard Java signed ordering convention.
     * Order criteria:
     * 1. Higher trust score ranks first (descending).
     * 2. Case-insensitive canteenCode alphabetical order (ascending).
     * 3. Case-sensitive canteenCode alphabetical order (ascending).
     * 4. Canteen name alphabetical order (ascending).
     *
     * @param other the canteen to compare against
     * @return negative if this ranks before other, positive if after, 0 if equal
     */
    public int compareTo(Canteen other) {
        if (other == null) {
            return -1;
        }

        // 1. Trust score descending (higher score first)
        if (this.trustScore != other.trustScore) {
            return Integer.compare(other.trustScore, this.trustScore);
        }

        // 2. Case-insensitive canteen code ascending
        int codeComparison = this.canteenCode.compareToIgnoreCase(other.canteenCode);
        if (codeComparison != 0) {
            return codeComparison;
        }

        // 3. Exact case tie-breaker
        int exactCodeComp = this.canteenCode.compareTo(other.canteenCode);
        if (exactCodeComp != 0) {
            return exactCodeComp;
        }

        // 4. Name tie-breaker
        return this.canteenName.compareTo(other.canteenName);
    }

    /**
     * Custom in-place ranking sort (Insertion Sort) without using built-in sort libraries.
     * Guarantees deterministic, stable ranking order.
     *
     * @param canteens input array of Canteen objects
     * @return sorted array of Canteens
     */
    public static Canteen[] rankCanteens(Canteen[] canteens) {
        if (canteens == null || canteens.length <= 1) {
            return canteens;
        }

        Canteen[] sorted = canteens.clone();

        for (int i = 1; i < sorted.length; i++) {
            Canteen key = sorted[i];
            int j = i - 1;

            // Move elements of sorted[0..i-1] that should rank after key
            while (j >= 0 && sorted[j].compareTo(key) > 0) {
                sorted[j + 1] = sorted[j];
                j--;
            }
            sorted[j + 1] = key;
        }

        return sorted;
    }

    public static void main(String[] args) {
        Canteen[] list = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats") // defaults to trust score 3
        };

        Canteen[] ranked = rankCanteens(list);

        System.out.print("[");
        for (int i = 0; i < ranked.length; i++) {
            System.out.print("\"" + ranked[i].getCanteenCode() + "\"");
            if (i < ranked.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
