package week11;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem 2: The Hackathon Banner Printer
 *
 * <p>Expands compressed hackathon banner design codes of the format {@code k[text]} where
 * {@code text} is repeated {@code k} times. Supports arbitrary nesting (e.g. {@code 2[x3[y]]z})
 * and multi-digit repeat counts without recursion using explicit stacks.
 *
 * <p><b>Algorithmic Strategy:</b>
 * Uses two stacks: one for repeat counts ({@code countStack}) and one for the string builders
 * created prior to each nested bracket ({@code textStack}).
 * <ol>
 *   <li>On a digit: accumulate multi-digit multiplier {@code k = k * 10 + (c - '0')}.</li>
 *   <li>On {@code '['}: push current count {@code k} onto {@code countStack}, push accumulated
 *       {@code currentText} onto {@code textStack}, reset {@code currentText} to a fresh builder,
 *       and reset {@code k = 0}.</li>
 *   <li>On {@code ']'}: pop repeat count and previous builder from stacks. Repeat {@code currentText}
 *       count times and append to the previous builder. Update {@code currentText = previousBuilder}.</li>
 *   <li>On regular characters: append directly to {@code currentText}.</li>
 * </ol>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Time Complexity:</b> O(L), where L is the total length of the fully expanded output string.
 *       Each character in the output is generated and copied proportional to its expansion.</li>
 *   <li><b>Space Complexity:</b> O(D + L), where D is the maximum nesting depth of brackets and
 *       L is the space needed to buffer string builders across stack frames.</li>
 * </ul>
 */
public class HackathonBannerPrinter {

    /**
     * Expands the compressed design code into the full banner text without recursion.
     *
     * @param code compressed banner code string
     * @return fully expanded banner text
     */
    public static String expandBanner(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Banner code cannot be null.");
        }
        if (code.isEmpty()) {
            return "";
        }

        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> textStack = new ArrayDeque<>();
        StringBuilder currentText = new StringBuilder();
        int k = 0;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);

            if (Character.isDigit(c)) {
                // Multi-digit repeat count
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                // Push repeat count and context built so far
                countStack.push(k);
                textStack.push(currentText);
                currentText = new StringBuilder();
                k = 0;
            } else if (c == ']') {
                // Pop count and previous context
                int repeatCount = countStack.pop();
                StringBuilder prevText = textStack.pop();
                for (int r = 0; r < repeatCount; r++) {
                    prevText.append(currentText);
                }
                currentText = prevText;
            } else {
                // Regular character outside or inside brackets
                currentText.append(c);
            }
        }

        return currentText.toString();
    }
}
