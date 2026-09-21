package week1;

/**
 * Problem 1: The Exam Hall Seat Duplication Checker
 *
 * Checks an array of assigned seat numbers for duplicate seat allocations
 * using primitive arrays and nested loops only (no Collections classes).
 */
public class SeatDuplicationChecker {

    /**
     * Checks if seatNumbers contains any duplicate seat numbers and prints the result.
     *
     * @param seatNumbers array of integer seat numbers
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static void checkDuplicateSeats(int[] seatNumbers) {
        if (seatNumbers == null) {
            throw new IllegalArgumentException("Seat numbers array cannot be null.");
        }
        if (seatNumbers.length == 0) {
            throw new IllegalArgumentException("Seat numbers array cannot be empty.");
        }

        boolean hasDuplicates = false;
        // Temporary array to keep track of duplicates already printed to avoid duplicate output lines
        int[] reportedDuplicates = new int[seatNumbers.length];
        int reportedCount = 0;

        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    // Check if this duplicate has already been reported
                    boolean alreadyReported = false;
                    for (int k = 0; k < reportedCount; k++) {
                        if (reportedDuplicates[k] == seatNumbers[i]) {
                            alreadyReported = true;
                            break;
                        }
                    }

                    if (!alreadyReported) {
                        System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                        reportedDuplicates[reportedCount++] = seatNumbers[i];
                        hasDuplicates = true;
                    }
                    break;
                }
            }
        }

        if (!hasDuplicates) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Duplicates present ---");
        int[] testCase1 = {101, 102, 103, 102, 105};
        checkDuplicateSeats(testCase1);

        System.out.println("\n--- Test Case 2: All unique ---");
        int[] testCase2 = {101, 102, 103, 104, 105};
        checkDuplicateSeats(testCase2);

        System.out.println("\n--- Test Case 3: Multiple different duplicates ---");
        int[] testCase3 = {201, 202, 201, 203, 204, 203};
        checkDuplicateSeats(testCase3);
    }
}
