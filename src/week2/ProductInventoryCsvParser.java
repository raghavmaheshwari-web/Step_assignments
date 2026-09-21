package week2;

/**
 * Problem 3: Product Inventory CSV Parser
 *
 * Parses a single CSV line containing "ProductName,SKU,Quantity",
 * validates field count, and formats the output.
 */
public class ProductInventoryCsvParser {

    /**
     * Parses a CSV inventory record and prints the formatted result or an error message.
     *
     * @param csvLine comma-separated record string
     */
    public static void parseInventoryRecord(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            System.out.println("Invalid Record");
            return;
        }

        // Split by comma
        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String productName = fields[0].trim();
        String sku = fields[1].trim();
        String quantity = fields[2].trim();

        if (productName.isEmpty() || sku.isEmpty() || quantity.isEmpty()) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Product: " + productName + " | SKU: " + sku + " | Qty: " + quantity);
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Valid record ---");
        parseInventoryRecord("Wireless Mouse,WM-2201,150");

        System.out.println("\n--- Test Case 2: Missing field (Invalid) ---");
        parseInventoryRecord("Wireless Mouse,150");

        System.out.println("\n--- Test Case 3: Extra field (Invalid) ---");
        parseInventoryRecord("Mechanical Keyboard,KB-100,75,Electronics");
    }
}
