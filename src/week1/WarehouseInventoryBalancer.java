package week1;

/**
 * Problem 4: The Warehouse Inventory Balancer
 *
 * Compares total inventory between Section A and Section B, verifies balance,
 * and identifies the item with the highest quantity across both sections.
 */
public class WarehouseInventoryBalancer {

    /**
     * Analyzes inventory across two warehouse sections and prints total quantities,
     * balance status, and the highest quantity item location.
     *
     * @param sectionA quantities of items in Section A
     * @param sectionB quantities of items in Section B
     * @throws IllegalArgumentException if arrays are null, empty, of unequal length, or have negative quantities
     */
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        if (sectionA == null || sectionB == null) {
            throw new IllegalArgumentException("Section inventory arrays cannot be null.");
        }
        if (sectionA.length == 0 || sectionB.length == 0) {
            throw new IllegalArgumentException("Section inventory arrays cannot be empty.");
        }
        if (sectionA.length != sectionB.length) {
            throw new IllegalArgumentException("Both sections must have equal number of items. " +
                    "Section A length: " + sectionA.length + ", Section B length: " + sectionB.length);
        }

        int totalA = 0;
        int totalB = 0;

        int highestQuantity = Integer.MIN_VALUE;
        String highestSection = "";
        int highestItemNumber = -1; // 1-based index

        // Scan Section A
        for (int i = 0; i < sectionA.length; i++) {
            int qty = sectionA[i];
            if (qty < 0) {
                throw new IllegalArgumentException("Inventory quantities cannot be negative. Found " + qty + " at Section A, index " + i);
            }
            totalA += qty;
            if (qty > highestQuantity) {
                highestQuantity = qty;
                highestSection = "Section A";
                highestItemNumber = i + 1; // 1-based item number
            }
        }

        // Scan Section B
        for (int i = 0; i < sectionB.length; i++) {
            int qty = sectionB[i];
            if (qty < 0) {
                throw new IllegalArgumentException("Inventory quantities cannot be negative. Found " + qty + " at Section B, index " + i);
            }
            totalB += qty;
            if (qty > highestQuantity) {
                highestQuantity = qty;
                highestSection = "Section B";
                highestItemNumber = i + 1; // 1-based item number
            }
        }

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        System.out.println("Section A Total: " + totalA + " | Section B Total: " + totalB +
                " | Status: " + status + " | Highest Quantity: " + highestQuantity +
                " (" + highestSection + ", Item " + highestItemNumber + ")");
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Balanced with highest in Section A ---");
        int[] secA1 = {20, 15, 30};
        int[] secB1 = {25, 10, 30};
        analyzeInventory(secA1, secB1);

        System.out.println("\n--- Test Case 2: Not Balanced with highest in Section B ---");
        int[] secA2 = {10, 20, 15};
        int[] secB2 = {30, 25, 40};
        analyzeInventory(secA2, secB2);

        System.out.println("\n--- Test Case 3: Single item per section ---");
        int[] secA3 = {50};
        int[] secB3 = {50};
        analyzeInventory(secA3, secB3);
    }
}
