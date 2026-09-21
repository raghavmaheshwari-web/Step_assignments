package week1;

/**
 * Problem 2: The Typing Speed Test Accuracy Checker
 *
 * Compares an original passage and a user-typed string character-by-character,
 * calculating the accuracy percentage and locating the first mismatch.
 */
public class TypingAccuracyChecker {

    /**
     * Compares original and typed strings and prints accuracy metrics.
     *
     * @param original the reference passage
     * @param typed the user's typed text
     * @throws IllegalArgumentException if either string is null or lengths differ
     */
    public static void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null) {
            throw new IllegalArgumentException("Original and typed strings cannot be null.");
        }
        if (original.length() != typed.length()) {
            throw new IllegalArgumentException("Original and typed strings must be of equal length. " +
                    "Original length: " + original.length() + ", Typed length: " + typed.length());
        }

        int totalChars = original.length();
        if (totalChars == 0) {
            System.out.println("Matched: 0/0 | Accuracy: 100.00% | No Mismatches");
            return;
        }

        int matchedCount = 0;
        int firstMismatchPosition = -1;
        char originalMismatchChar = '\0';
        char typedMismatchChar = '\0';

        for (int i = 0; i < totalChars; i++) {
            char origChar = original.charAt(i);
            char typedChar = typed.charAt(i);

            if (origChar == typedChar) {
                matchedCount++;
            } else {
                if (firstMismatchPosition == -1) {
                    firstMismatchPosition = i + 1; // 1-based position
                    originalMismatchChar = origChar;
                    typedMismatchChar = typedChar;
                }
            }
        }

        double accuracy = ((double) matchedCount / totalChars) * 100.0;
        String formattedAccuracy = String.format("%.2f%%", accuracy);

        StringBuilder output = new StringBuilder();
        output.append("Matched: ").append(matchedCount).append("/").append(totalChars)
              .append(" | Accuracy: ").append(formattedAccuracy).append(" | ");

        if (firstMismatchPosition == -1) {
            output.append("No Mismatches");
        } else {
            output.append("First Mismatch at position ").append(firstMismatchPosition)
                  .append(" ('").append(originalMismatchChar).append("' vs '")
                  .append(typedMismatchChar).append("')");
        }

        System.out.println(output.toString());
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Mismatch at position 11 ---");
        String original1 = "hello world";
        String typed1 = "hello worlt";
        checkTypingAccuracy(original1, typed1);

        System.out.println("\n--- Test Case 2: Perfect match ---");
        String original2 = "coding";
        String typed2 = "coding";
        checkTypingAccuracy(original2, typed2);

        System.out.println("\n--- Test Case 3: Multiple mismatches, first at position 1 ---");
        String original3 = "Java Programming";
        String typed3 = "Kava Proqramming";
        checkTypingAccuracy(original3, typed3);
    }
}
