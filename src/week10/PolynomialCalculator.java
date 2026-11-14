package week10;

import java.util.Objects;

/**
 * Problem 5: Polynomial Calculator
 *
 * <p>Stores algebraic polynomials as singly linked lists of terms (coefficient, exponent)
 * sorted in strictly decreasing order of exponent. Adds two polynomials in a single pass,
 * automatically omitting terms whose coefficients sum to 0, and formats the output into
 * standard mathematical polynomial notation.
 *
 * <p><b>Algorithmic Strategy:</b>
 * Performs a two-way merge similar to merge sort:
 * <ol>
 *   <li>Traverse lists {@code p} and {@code q} simultaneously.</li>
 *   <li>If {@code p.exp > q.exp}: copy term {@code p} and advance {@code p}.</li>
 *   <li>If {@code q.exp > p.exp}: copy term {@code q} and advance {@code q}.</li>
 *   <li>If {@code p.exp == q.exp}: add their coefficients. If {@code sum != 0}, append a new
 *       term with the combined coefficient; if {@code sum == 0}, skip the cancelled term.
 *       Advance both {@code p} and {@code q}.</li>
 *   <li>Append any remaining terms from {@code p} or {@code q}.</li>
 * </ol>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Time Complexity:</b> O(m + n), where m and n are the number of terms in polynomials
 *       {@code p} and {@code q}. Each input list is visited exactly once.</li>
 *   <li><b>Space Complexity:</b> O(m + n) to create the new merged polynomial linked list.</li>
 * </ul>
 */
public class PolynomialCalculator {

    /**
     * Singly linked list node representing a single polynomial term: coeff * x^exp.
     */
    public static class Node {
        public int coeff;
        public int exp;
        public Node next;

        public Node(int coeff, int exp) {
            this.coeff = coeff;
            this.exp = exp;
            this.next = null;
        }

        public Node(int coeff, int exp, Node next) {
            this.coeff = coeff;
            this.exp = exp;
            this.next = next;
        }

        @Override
        public String toString() {
            return coeff + "x^" + exp;
        }
    }

    /**
     * Adds two polynomials represented as linked lists of terms sorted by decreasing exponent.
     * Terms whose coefficients sum to 0 are excluded.
     *
     * @param p first polynomial
     * @param q second polynomial
     * @return head of the new polynomial linked list
     */
    public static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {
            if (p.exp > q.exp) {
                if (p.coeff != 0) {
                    tail.next = new Node(p.coeff, p.exp);
                    tail = tail.next;
                }
                p = p.next;
            } else if (q.exp > p.exp) {
                if (q.coeff != 0) {
                    tail.next = new Node(q.coeff, q.exp);
                    tail = tail.next;
                }
                q = q.next;
            } else {
                int sumCoeff = p.coeff + q.coeff;
                // Terms whose coefficients add up to 0 must not appear
                if (sumCoeff != 0) {
                    tail.next = new Node(sumCoeff, p.exp);
                    tail = tail.next;
                }
                p = p.next;
                q = q.next;
            }
        }

        while (p != null) {
            if (p.coeff != 0) {
                tail.next = new Node(p.coeff, p.exp);
                tail = tail.next;
            }
            p = p.next;
        }

        while (q != null) {
            if (q.coeff != 0) {
                tail.next = new Node(q.coeff, q.exp);
                tail = tail.next;
            }
            q = q.next;
        }

        return dummy.next;
    }

    /**
     * Formats a polynomial linked list into standard mathematical string form
     * (e.g. "5x^3 + 9x^2 - 3x + 3").
     *
     * @param head head of the polynomial linked list
     * @return formatted polynomial string, or "0" if empty/zero
     */
    public static String toStandardForm(Node head) {
        if (head == null) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        Node curr = head;
        boolean firstTerm = true;

        while (curr != null) {
            int coeff = curr.coeff;
            int exp = curr.exp;

            if (coeff != 0) {
                // Determine sign and spacing
                if (firstTerm) {
                    if (coeff < 0) {
                        sb.append("-");
                    }
                    firstTerm = false;
                } else {
                    if (coeff < 0) {
                        sb.append(" - ");
                    } else {
                        sb.append(" + ");
                    }
                }

                long absCoeff = Math.abs((long) coeff);

                // Format coefficient and exponent
                if (exp == 0) {
                    sb.append(absCoeff);
                } else if (exp == 1) {
                    if (absCoeff == 1) {
                        sb.append("x");
                    } else {
                        sb.append(absCoeff).append("x");
                    }
                } else {
                    if (absCoeff == 1) {
                        sb.append("x^").append(exp);
                    } else {
                        sb.append(absCoeff).append("x^").append(exp);
                    }
                }
            }

            curr = curr.next;
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    /**
     * Helper to construct a polynomial list from alternating (coeff, exp) pairs.
     * Example: buildPolynomial(5, 3, 4, 2, 2, 0) represents 5x^3 + 4x^2 + 2.
     */
    public static Node buildPolynomial(int... terms) {
        if (terms == null || terms.length == 0) {
            return null;
        }
        if (terms.length % 2 != 0) {
            throw new IllegalArgumentException("Terms must be specified as alternating (coeff, exp) pairs.");
        }

        Node dummy = new Node(0, 0);
        Node tail = dummy;
        for (int i = 0; i < terms.length; i += 2) {
            int coeff = terms[i];
            int exp = terms[i + 1];
            if (coeff != 0) {
                tail.next = new Node(coeff, exp);
                tail = tail.next;
            }
        }
        return dummy.next;
    }
}
