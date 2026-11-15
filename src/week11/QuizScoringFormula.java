package week11;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem 3: The Quiz Scoring Formula
 *
 * <p>Evaluates arithmetic scoring formulas containing non-negative integers (possibly multi-digit),
 * the operators {@code +}, {@code -}, {@code *}, {@code /}, parentheses {@code (}, {@code )},
 * and whitespace in a single left-to-right scan using an operand stack and an operator stack.
 *
 * <p><b>Algorithmic Strategy (Dijkstra's Two-Stack Direct Infix Evaluation):</b>
 * <ol>
 *   <li>Maintain an operand stack ({@code values}) and an operator stack ({@code ops}).</li>
 *   <li>Skip any whitespace characters.</li>
 *   <li>On digits: parse the complete multi-digit integer and push to {@code values}.</li>
 *   <li>On {@code '('}: push to {@code ops}.</li>
 *   <li>On {@code ')'}: pop and apply operators until matching {@code '('} is encountered, then discard {@code '('}.</li>
 *   <li>On operator ({@code +}, {@code -}, {@code *}, {@code /}):
 *       While {@code ops} is non-empty and the top operator has greater or equal precedence
 *       (and is not {@code '('}), pop and apply it to the top two values. Then push current operator.</li>
 *   <li>At the end of the expression, apply all remaining operators on the stack.</li>
 * </ol>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Time Complexity:</b> O(N), where N is the length of the expression. Every character is
 *       scanned once, and each operand and operator is pushed and popped at most once.</li>
 *   <li><b>Space Complexity:</b> O(N) auxiliary space to hold operands and operators on the stacks.</li>
 * </ul>
 */
public class QuizScoringFormula {

    /**
     * Evaluates the infix mathematical expression directly without postfix conversion.
     *
     * @param expr arithmetic expression string
     * @return integer result of the evaluation
     */
    public static int evaluate(String expr) {
        if (expr == null) {
            throw new IllegalArgumentException("Expression cannot be null.");
        }

        Deque<Integer> values = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();

        int n = expr.length();
        for (int i = 0; i < n; i++) {
            char c = expr.charAt(i);

            // 1. Skip whitespace
            if (Character.isWhitespace(c)) {
                continue;
            }

            // 2. Parse multi-digit number
            if (Character.isDigit(c)) {
                int num = 0;
                while (i < n && Character.isDigit(expr.charAt(i))) {
                    num = num * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                values.push(num);
                i--; // adjust loop index
            } else if (c == '(') {
                // 3. Opening parenthesis
                ops.push(c);
            } else if (c == ')') {
                // 4. Closing parenthesis: evaluate until matching '('
                while (!ops.isEmpty() && ops.peek() != '(') {
                    applyOperation(values, ops.pop());
                }
                if (ops.isEmpty() || ops.peek() != '(') {
                    throw new IllegalArgumentException("Mismatched parentheses in expression: " + expr);
                }
                ops.pop(); // discard '('
            } else if (isOperator(c)) {
                // 5. Operator: resolve operators with >= precedence
                while (!ops.isEmpty() && ops.peek() != '(' && precedence(ops.peek()) >= precedence(c)) {
                    applyOperation(values, ops.pop());
                }
                ops.push(c);
            } else {
                throw new IllegalArgumentException("Invalid character in expression: " + c);
            }
        }

        // 6. Apply all remaining operators
        while (!ops.isEmpty()) {
            char op = ops.pop();
            if (op == '(' || op == ')') {
                throw new IllegalArgumentException("Mismatched parentheses in expression: " + expr);
            }
            applyOperation(values, op);
        }

        if (values.isEmpty()) {
            throw new IllegalArgumentException("Empty or invalid expression: " + expr);
        }

        return values.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char op) {
        if (op == '*' || op == '/') {
            return 2;
        }
        if (op == '+' || op == '-') {
            return 1;
        }
        return 0;
    }

    private static void applyOperation(Deque<Integer> values, char op) {
        if (values.size() < 2) {
            throw new IllegalArgumentException("Insufficient operands for operator: " + op);
        }
        int b = values.pop();
        int a = values.pop();

        switch (op) {
            case '+':
                values.push(a + b);
                break;
            case '-':
                values.push(a - b);
                break;
            case '*':
                values.push(a * b);
                break;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero in formula.");
                }
                values.push(a / b);
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }
}
