package week5;

/**
 * Week5TestRunner
 *
 * Unified test suite that executes and verifies all 5 Category A problems
 * for the Week 5 STEP Assignment on Access Modifiers & Encapsulation.
 */
public class Week5TestRunner {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("            STEP SEM-3 WEEK 5 ASSIGNMENT TEST SUITE            ");
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
        System.out.println("Problem 1: Membership Field Reach Checker");
        System.out.println("---------------------------------------------------------------");

        System.out.println("classifyAccess(\"private\", \"SAME_CLASS\")");
        System.out.println("Expected: ALLOWED | Actual: " + AccessChecker.classifyAccess("private", "SAME_CLASS"));

        System.out.println("\nclassifyAccess(\"protected\", \"DIFFERENT_PACKAGE\")");
        System.out.println("Expected: DENIED  | Actual: " + AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println("\nsummarizeByModifier:");
        System.out.println("Expected: private: 1 allowed / 1 denied | default: 1 allowed / 1 denied | protected: 2 allowed / 0 denied | public: 1 allowed / 0 denied");
        System.out.println("Actual:   " + AccessChecker.summarizeByModifier(attempts));

        System.out.println("\nConstructor validation test (membershipId < 4 characters):");
        try {
            new LibraryMember("LB9", "BR1", 0, "Priya Nair");
            System.out.println("Actual:   construction succeeded (UNEXPECTED)");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected: construction rejected\nActual:   construction rejected (" + e.getMessage() + ")");
        }

        try {
            LibraryMember validMember = new LibraryMember("LB94", "BR1", 0, "Priya Nair");
            System.out.println("Valid ID 'LB94' accepted successfully: " + validMember.getMembershipId());
        } catch (Exception e) {
            System.out.println("Error creating valid member: " + e.getMessage());
        }
        System.out.println();
    }

    private static void testProblem2() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 2: Reference Desk Subclass Reach");
        System.out.println("---------------------------------------------------------------");

        System.out.println("classifyAccess(\"protected\", \"SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE\")");
        System.out.println("Expected: ALLOWED | Actual: " +
                AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));

        System.out.println("\nclassifyAccess(\"protected\", \"SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE\")");
        System.out.println("Expected: DENIED  | Actual: " +
                AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));

        System.out.println("\ndescribeContext(\"SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE\")");
        System.out.println("Expected: Subclass Different Package Own Type");
        System.out.println("Actual:   " + AccessChecker.describeContext("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println();
    }

    private static void testProblem3() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 3: Book Copy Circulation Guard");
        System.out.println("---------------------------------------------------------------");

        System.out.println("new BookInventory(0):");
        try {
            new BookInventory(0);
            System.out.println("Actual:   construction succeeded (UNEXPECTED)");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected: construction rejected\nActual:   construction rejected (" + e.getMessage() + ")");
        }

        BookInventory b = new BookInventory(3);
        System.out.println("\nInitial available copies: " + b.getCopiesAvailable());

        // 4 checkouts on an inventory of 3
        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut(); // Exceeds available copies
        System.out.println("After 4 checkOut() calls on 3 copies:");
        System.out.println("Expected: 0 | Actual: " + b.getCopiesAvailable());

        // 4 check-ins on an inventory of 3
        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn(); // Exceeds total capacity
        System.out.println("\nAfter 4 checkIn() calls on 3 copies:");
        System.out.println("Expected: 3 | Actual: " + b.getCopiesAvailable());
        System.out.println();
    }

    private static void testProblem4() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 4: LibraryMember JavaBean & Write-Once / Write-Only Properties");
        System.out.println("---------------------------------------------------------------");

        LibraryMemberBean m1 = new LibraryMemberBean("Priya Nair");
        System.out.println("new LibraryMemberBean(\"Priya Nair\").getMembershipId()");
        System.out.println("Expected: null | Actual: " + m1.getMembershipId());

        LibraryMemberBean m2 = new LibraryMemberBean("LIB-8841", "Priya Nair");
        System.out.println("\nnew LibraryMemberBean(\"LIB-8841\", \"Priya Nair\").getMembershipId()");
        System.out.println("Expected: LIB-8841 | Actual: " + m2.getMembershipId());

        LibraryMemberBean m3 = new LibraryMemberBean();
        m3.setMembershipId("LIB-8841");
        m3.setMembershipId("FAKE-0000"); // Second write must be silently ignored
        System.out.println("\nWrite-once test on setMembershipId(\"LIB-8841\") followed by setMembershipId(\"FAKE-0000\"):");
        System.out.println("Expected: LIB-8841 | Actual: " + m3.getMembershipId());

        // Test write-only property
        m3.setSecurityAnswer("FirstPetName");
        System.out.println("Security answer set successfully (no getter method exposed).");
        System.out.println();
    }

    private static void testProblem5() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 5: Immutable Loan Receipt & Nightly Circulation Ledger");
        System.out.println("---------------------------------------------------------------");

        System.out.println("Validation test: new LoanReceipt with invalid ID 'bad':");
        try {
            new LoanReceipt("LIB-8841", new String[]{"BK-100", "bad"});
            System.out.println("Actual:   construction succeeded (UNEXPECTED)");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected: construction rejected\nActual:   construction rejected (" + e.getMessage() + ")");
        }

        System.out.println("\nImmutability & Defensive Copying Test:");
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println("After mutating returned array: r.getBookIds()[0]");
        System.out.println("Expected: BK-100 | Actual: " + r.getBookIds()[0]);

        System.out.println("\nWither Test (withCorrectedBookId):");
        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-999");
        System.out.println("Original receipt book[1]:  " + r.getBookIds()[1]);
        System.out.println("Corrected receipt book[1]: " + corrected.getBookIds()[1]);

        System.out.println("\nNightly Batch Reconciliation Test:");
        LoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println("Expected: 2 processed | 1 null skipped | 1 reference-only | 1 regular");
        System.out.println("Actual:   " + NightlyCirculationLedger.processNightlyCirculation(receipts));
        System.out.println();
    }
}
