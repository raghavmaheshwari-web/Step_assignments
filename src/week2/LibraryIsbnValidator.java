package week2;

/**
 * Problem 4: Library ISBN Normalizer & Validator
 *
 * Normalizes book intake codes by trimming whitespace and uppercasing the publisher prefix,
 * then validates length and character types using Character methods (no regex).
 */
public class LibraryIsbnValidator {

    /**
     * Normalizes raw code by trimming whitespace and capitalizing the first 3 letters.
     *
     * @param raw the unscanned or raw code string
     * @return normalized code string
     */
    public static String normalizeCode(String raw) {
        if (raw == null) {
            return "";
        }
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        // Uppercase only the first 3 characters using substring() and concatenation
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    /**
     * Validates normalized code and returns formatted display line or prints error reason.
     *
     * @param code the normalized code string
     * @return formatted string if valid, or error message if invalid
     */
    public static String validateAndFormat(String code) {
        if (code == null || code.length() != 13) {
            String error = "Invalid: code must be exactly 13 characters";
            System.out.println(error);
            return error;
        }

        // Validate first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                String error = "Invalid: publisher code must be 3 letters";
                System.out.println(error);
                return error;
            }
        }

        // Validate remaining 10 characters are digits (4 for year + 6 for catalog)
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                String error = "Invalid: body must be 10 digits";
                System.out.println(error);
                return error;
            }
        }

        // Build formatted display line using StringBuilder
        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("] YEAR: ")
          .append(year).append(" | CATALOG: ")
          .append(catalog);

        String result = sb.toString();
        System.out.println(result);
        return result;
    }

    /**
     * Convenience method to normalize, validate, and format in one step.
     */
    public static void processCode(String raw) {
        String normalized = normalizeCode(raw);
        validateAndFormat(normalized);
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Valid code with spaces & lowercase prefix ---");
        processCode(" pen2026004251 ");

        System.out.println("\n--- Test Case 2: Invalid publisher code (digits in prefix) ---");
        processCode("12N2026004251");

        System.out.println("\n--- Test Case 3: Invalid length ---");
        processCode("pen2026");

        System.out.println("\n--- Test Case 4: Invalid body (non-digit in catalog) ---");
        processCode("OUP2024ABC123");
    }
}
