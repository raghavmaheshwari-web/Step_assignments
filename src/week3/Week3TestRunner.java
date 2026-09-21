package week3;

/**
 * Week3TestRunner
 *
 * Unified test suite that executes and verifies all 5 Category A problems
 * for the Week 3 STEP Assignment.
 */
public class Week3TestRunner {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("            STEP SEM-3 WEEK 3 ASSIGNMENT TEST SUITE            ");
        System.out.println("===============================================================\n");

        testProblemF1();
        testProblemF2();
        testProblemF3();
        testProblemF4();
        testProblemF5();

        System.out.println("\n===============================================================");
        System.out.println("                 ALL ASSIGNMENT TESTS COMPLETED                ");
        System.out.println("===============================================================");
    }

    private static void testProblemF1() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem F1: Library Fine System (Instance vs Static Methods)");
        System.out.println("---------------------------------------------------------------");
        LibraryFineSystem.runDemo();
        System.out.println();
    }

    private static void testProblemF2() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem F2: Extending Employee Without Touching It");
        System.out.println("---------------------------------------------------------------");
        EmployeeInheritanceHierarchy.runDemo();
        System.out.println();
    }

    private static void testProblemF3() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem F3: Parking Slot Allocation & Null Safety");
        System.out.println("---------------------------------------------------------------");
        ParkingSlotAllocationSystem.runDemo();
        System.out.println();
    }

    private static void testProblemF4() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem F4: Designing Instance/Static Boundary (Library Member)");
        System.out.println("---------------------------------------------------------------");
        LibraryMemberBoundaryDemo.runDemo();
        System.out.println();
    }

    private static void testProblemF5() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem F5: Capstone HR + Parking Allocation Mini-System");
        System.out.println("---------------------------------------------------------------");
        HrParkingMiniSystem.runDemo();
        System.out.println();
    }
}
