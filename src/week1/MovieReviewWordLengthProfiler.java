package week1;

/**
 * Problem 5: The Movie Review Word Length Profiler
 *
 * Scans a movie review, splits it into words, and categorizes each word
 * by its length into Short (1–4 letters), Medium (5–8 letters), or Long (9+ letters).
 */
public class MovieReviewWordLengthProfiler {

    /**
     * Splits a review into words and prints counts for Short, Medium, and Long words.
     *
     * @param review string containing the review text
     * @throws IllegalArgumentException if review is null or empty
     */
    public static void classifyWordLengths(String review) {
        if (review == null) {
            throw new IllegalArgumentException("Review string cannot be null.");
        }

        String trimmed = review.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Review string cannot be empty.");
        }

        // Split by one or more whitespace characters
        String[] words = trimmed.split("\\s+");

        int shortCount = 0;   // 1 to 4 letters
        int mediumCount = 0;  // 5 to 8 letters
        int longCount = 0;    // 9 or more letters

        for (String word : words) {
            // Strip any surrounding or trailing punctuation (e.g., "fantastic!" -> "fantastic")
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "");
            int length = cleanWord.length();

            if (length == 0) {
                continue; // Skip standalone punctuation tokens
            }

            if (length >= 1 && length <= 4) {
                shortCount++;
            } else if (length >= 5 && length <= 8) {
                mediumCount++;
            } else {
                longCount++;
            }
        }

        System.out.println("Short: " + shortCount + " | Medium: " + mediumCount + " | Long: " + longCount);
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Sample from assignment ---");
        String review1 = "This movie was absolutely fantastic and thrilling";
        classifyWordLengths(review1);

        System.out.println("\n--- Test Case 2: Review with punctuation ---");
        String review2 = "A truly masterpiece! Unbelievable acting, superb cinematography, and gripping story.";
        classifyWordLengths(review2);

        System.out.println("\n--- Test Case 3: Short review ---");
        String review3 = "Good film!";
        classifyWordLengths(review3);
    }
}
