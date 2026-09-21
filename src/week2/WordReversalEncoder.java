package week2;

/**
 * Problem 2: Word Reversal Encoder
 *
 * Reverses each individual word in a sentence while maintaining the original word order,
 * using split(), a loop, and StringBuilder.
 */
public class WordReversalEncoder {

    /**
     * Reverses each word in the sentence and prints the resulting encoded sentence.
     *
     * @param sentence sentence with words separated by spaces
     * @return the encoded sentence with each word reversed
     * @throws IllegalArgumentException if sentence is null
     */
    public static String reverseEachWord(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("Sentence cannot be null.");
        }
        if (sentence.isEmpty()) {
            System.out.println("");
            return "";
        }

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            // Build the reversed word using a loop and StringBuilder
            StringBuilder reversedWord = new StringBuilder();
            for (int j = word.length() - 1; j >= 0; j--) {
                reversedWord.append(word.charAt(j));
            }

            result.append(reversedWord);

            // Append space between words, but not after the last word
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        String encodedSentence = result.toString();
        System.out.println(encodedSentence);
        return encodedSentence;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: 'hello club' ---");
        reverseEachWord("hello club");

        System.out.println("\n--- Test Case 2: 'Java Programming Language' ---");
        reverseEachWord("Java Programming Language");

        System.out.println("\n--- Test Case 3: Single word ---");
        reverseEachWord("Antigravity");
    }
}
