package week2;

/**
 * Week2TestRunner
 *
 * Unified test suite that executes and verifies all 5 problems
 * from the Week 2 STEP Assignment.
 */
public class Week2TestRunner {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("            STEP SEM-3 WEEK 2 ASSIGNMENT TEST SUITE            ");
        System.out.println("===============================================================\n");

        testProblem1();
        testProblem2();
        testProblem3();
        testProblem4();
        testProblem5();

        System.out.println("\n===============================================================");
        System.out.println("                 ALL ASSIGNMENT TESTS COMPLETED                ");
        System.out.println("===============================================================");
    }

    private static void testProblem1() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 1: ATM PIN Length Validator");
        System.out.println("---------------------------------------------------------------");

        System.out.print("Input: \"482\"\nExpected: Invalid PIN — must be exactly 4 digits.\nActual:   ");
        AtmPinLengthValidator.checkPinLength("482");

        System.out.print("\nInput: \"4820\"\nExpected: PIN length OK.\nActual:   ");
        AtmPinLengthValidator.checkPinLength("4820");
        System.out.println();
    }

    private static void testProblem2() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 2: Word Reversal Encoder");
        System.out.println("---------------------------------------------------------------");

        System.out.print("Input: \"hello club\"\nExpected: olleh bulc\nActual:   ");
        WordReversalEncoder.reverseEachWord("hello club");
        System.out.println();
    }

    private static void testProblem3() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 3: Product Inventory CSV Parser");
        System.out.println("---------------------------------------------------------------");

        System.out.print("Input: \"Wireless Mouse,WM-2201,150\"\nExpected: Product: Wireless Mouse | SKU: WM-2201 | Qty: 150\nActual:   ");
        ProductInventoryCsvParser.parseInventoryRecord("Wireless Mouse,WM-2201,150");

        System.out.print("\nInput: \"Wireless Mouse,150\"\nExpected: Invalid Record\nActual:   ");
        ProductInventoryCsvParser.parseInventoryRecord("Wireless Mouse,150");
        System.out.println();
    }

    private static void testProblem4() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 4: Library ISBN Normalizer & Validator");
        System.out.println("---------------------------------------------------------------");

        System.out.print("Input: \" pen2026004251 \"\nExpected: [PEN] YEAR: 2026 | CATALOG: 004251\nActual:   ");
        LibraryIsbnValidator.processCode(" pen2026004251 ");

        System.out.print("\nInput: \"12N2026004251\"\nExpected: Invalid: publisher code must be 3 letters\nActual:   ");
        LibraryIsbnValidator.processCode("12N2026004251");
        System.out.println();
    }

    private static void testProblem5() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 5: Stop-Word-Filtered Word Frequency Report");
        System.out.println("---------------------------------------------------------------");

        System.out.println("Input: \"The mentor was great, the session was great and clear.\"");
        System.out.println("Expected:\ngreat: 2\nmentor: 1\nsession: 1\nclear: 1\n\nActual:");
        StopWordFrequencyReporter.printFilteredWordFrequency("The mentor was great, the session was great and clear.");
        System.out.println();
    }
}
