package week2;

import java.util.*;

/**
 * Problem 5: Stop-Word-Filtered Word Frequency Report
 *
 * Normalizes feedback text by lowercasing and stripping punctuation using replace(),
 * filters out common stop words, counts frequencies using a Map, and prints
 * the word frequency report sorted in descending order by count.
 */
public class StopWordFrequencyReporter {

    // Fixed list of stop words to filter out
    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "the", "was", "and", "a", "is", "of", "in"
    ));

    /**
     * Cleans the feedback text, filters out stop words, counts frequencies,
     * and prints word counts in descending order.
     *
     * @param feedback paragraph of feedback text
     */
    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            System.out.println("No feedback content provided.");
            return;
        }

        // 1. Convert to lowercase
        String cleaned = feedback.toLowerCase();

        // 2. Strip punctuation such as periods, commas, exclamation/question marks using replace()
        cleaned = cleaned.replace(".", "")
                         .replace(",", "")
                         .replace("!", "")
                         .replace("?", "")
                         .replace(";", "")
                         .replace(":", "")
                         .replace("\"", "");

        // 3. Split by whitespace
        String[] words = cleaned.trim().split("\\s+");

        // 4. Count frequency for non-stop words
        Map<String, Integer> frequencyMap = new LinkedHashMap<>();
        for (String word : words) {
            if (word.isEmpty() || STOP_WORDS.contains(word)) {
                continue;
            }
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // 5. Sort by count in descending order
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // 6. Print results
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Sample from assignment ---");
        String sample1 = "The mentor was great, the session was great and clear.";
        printFilteredWordFrequency(sample1);

        System.out.println("\n--- Test Case 2: Multi-sentence feedback with various stop words ---");
        String sample2 = "The code is clean, and the explanation of the concept was clear and in depth.";
        printFilteredWordFrequency(sample2);
    }
}
