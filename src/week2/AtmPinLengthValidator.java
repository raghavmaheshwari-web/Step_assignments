package week2;

/**
 * Problem 1: ATM PIN Length Validator
 *
 * Validates that a user's entered ATM PIN is exactly 4 characters/digits
 * using length() and a single if/else check without loops.
 */
public class AtmPinLengthValidator {

    /**
     * Checks if the PIN is exactly 4 digits long and prints the validation result.
     *
     * @param pin the entered PIN string
     */
    public static void checkPinLength(String pin) {
        if (pin == null || pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: 3 digits (Invalid) ---");
        checkPinLength("482");

        System.out.println("\n--- Test Case 2: 4 digits (Valid) ---");
        checkPinLength("4820");

        System.out.println("\n--- Test Case 3: 5 digits (Invalid) ---");
        checkPinLength("12345");
    }
}
