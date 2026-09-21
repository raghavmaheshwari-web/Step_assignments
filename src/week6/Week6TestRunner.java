package week6;

import java.util.Arrays;

/**
 * Week6TestRunner
 *
 * Unified test suite verifying all 5 Category A problems for Week 6
 * on OOP Inheritance and Polymorphism.
 */
public class Week6TestRunner {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("            STEP SEM-3 WEEK 6 ASSIGNMENT TEST SUITE            ");
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
        System.out.println("Problem 1: Race Entry Foundation & Batch Bib Validator");
        System.out.println("---------------------------------------------------------------");

        System.out.println("Construction test: new RaceEntry(\"B1\", 50):");
        try {
            new RaceEntry("B1", 50);
            System.out.println("Actual:   construction succeeded (UNEXPECTED)");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected: construction rejected\nActual:   construction rejected (" + e.getMessage() + ")");
        }

        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        System.out.println("\nRunnerEntry payment test (80 entry fee, 30 paid):");
        System.out.println("Expected balance due: 50.0 | Actual: " + r.getBalanceDue());

        String[] batch = {"BIB1", "B1", "BIB2"};
        System.out.println("\nregisterBatch({\"BIB1\", \"B1\", \"BIB2\"}, 80):");
        System.out.println("Expected: Registered: 2 | Rejected: 1");
        System.out.println("Actual:   " + RaceEntry.registerBatch(batch, 80));
        System.out.println();
    }

    private static void testProblem2() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 2: Three Shapes of One Race Family");
        System.out.println("---------------------------------------------------------------");

        RunnerEntry runner = new RunnerEntry("BIB2001", 80, "Open 10K");
        EliteRunnerEntry elite = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relay = new RelayTeamEntry("BIB4001", 300, 4);

        System.out.println("runner.announce():\n" + runner.announce());
        System.out.println("\nelite.announce():\n" + elite.announce());
        System.out.println("\nrelay.announce():\n" + relay.announce());

        System.out.println("\nclassifyGeneration(eliteEntry):");
        System.out.println("Expected: Multilevel descendant (3 generations deep)");
        System.out.println("Actual:   " + AnnouncerBoard.classifyGeneration(elite));

        System.out.println("\nclassifyGeneration(relayEntry):");
        System.out.println("Expected: Hierarchical sibling (independent branch)");
        System.out.println("Actual:   " + AnnouncerBoard.classifyGeneration(relay));

        RaceEntry[] field = {runner, elite, relay};
        System.out.println("\ngetTotalBalanceDue({ runnerEntry, eliteEntry, relayEntry }):");
        System.out.println("Expected: 530.0 | Actual: " + AnnouncerBoard.getTotalBalanceDue(field));
        System.out.println();
    }

    private static void testProblem3() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 3: The Late-Withdrawal Penalty Override & Audit Trail");
        System.out.println("---------------------------------------------------------------");

        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20); // RunnerEntry doubles 20 to 40
        System.out.println("r.getBalanceDue() after pay(30) and applyLateFee(20):");
        System.out.println("Expected: 90.0 | Actual: " + r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        System.out.println("\nRecorded late fee history:");
        System.out.println("Expected: [40.0] | Actual: " + Arrays.toString(history));

        // Tamper test
        history[0] = 999.0;
        System.out.println("\nAfter tampering with returned array history[0] = 999.0:");
        System.out.println("Expected: [40.0] | Actual: " + Arrays.toString(r.getLateFeeHistory()));
        System.out.println();
    }

    private static void testProblem4() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 4: The Race-Day Announcer Board");
        System.out.println("---------------------------------------------------------------");

        RunnerEntry runner = new RunnerEntry("BIB2001", 80, "Open 10K");
        runner.pay(30);
        runner.applyLateFee(20); // balance becomes 90.0

        RelayTeamEntry relay = new RelayTeamEntry("BIB4001", 300, 4);

        RaceEntry[] fleet = {runner, relay};
        System.out.println("announceAll(fleet):");
        System.out.println("Expected: Runner Entry | Bib: BIB2001 | Category: Open 10K | Balance: 90.0 | Relay Team | Bib: BIB4001 | Team Size: 4 | Balance: 300.0 [Team size via downcast: 4] | ");
        System.out.println("Actual:   " + AnnouncerBoard.announceAll(fleet));
        System.out.println();
    }

    private static void testProblem5() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 5: Race-Wide Bib Issuance, Discount Codes & Nightly Settlement");
        System.out.println("---------------------------------------------------------------");

        System.out.println("isValidDiscountCode tests:");
        System.out.println("isValidDiscountCode(\"M123A\") -> Expected: true  | Actual: " + RaceEntry.isValidDiscountCode("M123A"));
        System.out.println("isValidDiscountCode(\"M12A\")  -> Expected: false | Actual: " + RaceEntry.isValidDiscountCode("M12A"));
        System.out.println("isValidDiscountCode(\"X123A\") -> Expected: false | Actual: " + RaceEntry.isValidDiscountCode("X123A"));

        System.out.println("\nOverloaded pay method test:");
        RaceEntry sampleEntry = new RaceEntry("BIB999", 100);
        sampleEntry.pay(10, "UPI");

        EliteRunnerEntry eliteEntry = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300, 4);

        RaceEntry[] nightlyBatch = {eliteEntry, null, relayEntry};
        System.out.println("\nsettleNight({ eliteEntry, null, relayEntry }):");
        System.out.println("Expected: 2 processed | 1 null skipped | 1 relay | 1 individual");
        System.out.println("Actual:   " + RaceEntry.settleNight(nightlyBatch));

        System.out.println("\nRaceEntry.getBibCounter(): " + RaceEntry.getBibCounter());
        System.out.println();
    }
}
