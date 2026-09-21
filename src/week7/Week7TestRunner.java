package week7;

/**
 * Week7TestRunner
 *
 * Unified test suite verifying all 5 Category A problems for Week 7
 * on Abstraction and Interfaces.
 */
public class Week7TestRunner {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("            STEP SEM-3 WEEK 7 ASSIGNMENT TEST SUITE            ");
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
        System.out.println("Problem 1: Basic Drawing Canvas");
        System.out.println("---------------------------------------------------------------");

        CircleShape c = new CircleShape(5.0);
        System.out.println("CircleShape c = new CircleShape(5.0); c.calculateArea():");
        System.out.println("Expected: ~78.54 | Actual: " + String.format("%.2f", c.calculateArea()));

        SquareShape sq = new SquareShape(4.0);
        System.out.println("\nSquareShape sq = new SquareShape(4.0); sq.calculateArea():");
        System.out.println("Expected: 16.0 | Actual: " + sq.calculateArea());

        sq.scale(2.0); // uses single-factor overload
        System.out.println("\nAfter sq.scale(2.0); sq.calculateArea():");
        System.out.println("Expected: 64.0 | Actual: " + sq.calculateArea());

        System.out.println("\nprintArea(c):");
        ShapeAreaPrinter.printArea(c);
        System.out.println();
    }

    private static void testProblem2() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 2: One-Click Data Export");
        System.out.println("---------------------------------------------------------------");

        ExportManager.resetExportCount();

        ReportGenerator r = new ReportGenerator("Sales Q1");
        System.out.println("r.exportData():");
        System.out.println("Expected: Exported report: Sales Q1");
        System.out.println("Actual:   " + r.exportData());

        UserProfile u = new UserProfile("jane_doe");
        System.out.println("\nu.exportData():");
        System.out.println("Expected: Exported profile: jane_doe");
        System.out.println("Actual:   " + u.exportData());

        System.out.println("\nexportAll(new Exportable[]{ ref, u }):");
        ExportManager.resetExportCount(); // Reset for batch demo
        Exportable ref = r;
        ExportManager.exportAll(new Exportable[]{ ref, u });

        System.out.println("\ngetTotalExports():");
        System.out.println("Expected: 2 | Actual: " + ExportManager.getTotalExports());
        System.out.println();
    }

    private static void testProblem3() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 3: Fleet Maintenance Tracker");
        System.out.println("---------------------------------------------------------------");

        Forklift f = new Forklift("FL-22");
        f.addMileage(120);
        System.out.println("Forklift f = new Forklift(\"FL-22\"); f.addMileage(120); f.getMileage():");
        System.out.println("Expected: 120.0 | Actual: " + f.getMileage());

        System.out.println("\nf.performMaintenance():");
        System.out.println("Expected: Forklift FL-22: hydraulic and fork inspection complete");
        System.out.println("Actual:   " + f.performMaintenance());

        HeavyDutyForklift hd = new HeavyDutyForklift("HD-9");
        System.out.println("\nhd.performMaintenance():");
        System.out.println("Expected: Forklift HD-9: hydraulic and fork inspection complete | high-pressure hydraulic check complete");
        System.out.println("Actual:   " + hd.performMaintenance());

        System.out.println("\ngetInsuranceIfApplicable(f):");
        System.out.println("Expected: Insured under fleet policy - Asset FL-22");
        System.out.println("Actual:   " + FleetTracker.getInsuranceIfApplicable(f));
        System.out.println();
    }

    private static void testProblem4() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 4: Arena Battle Simulator");
        System.out.println("---------------------------------------------------------------");

        Warrior w = new Warrior("Kael");
        System.out.println("w.attack():\nExpected: Kael strikes with a blade\nActual:   " + w.attack());
        System.out.println("\nw.attack(\"Iron Sword\"):\nExpected: Kael strikes with an Iron Sword\nActual:   " + w.attack("Iron Sword"));
        System.out.println("\nw.defend():\nExpected: Kael raises a shield\nActual:   " + w.defend());
        System.out.println("\nw.getSpecialMove():\nExpected: Kael unleashes Whirlwind Slash\nActual:   " + w.getSpecialMove());

        Trap t = new Trap("Spike Pit");
        System.out.println("\nt.defend():\nExpected: Spike Pit triggers automatically\nActual:   " + t.defend());

        System.out.println("\nresolveDefense(new Defendable[]{ w, t }):");
        ArenaBattleSimulator.resolveDefense(new Defendable[]{ w, t });
        System.out.println();
    }

    private static void testProblem5() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 5: Connected Home Control Panel");
        System.out.println("---------------------------------------------------------------");

        HomeDevice.resetDeviceCounter();

        WashingMachine wm = new WashingMachine(500.0);
        System.out.println("wm.activate():\nExpected: Washing machine HD-1001 started a cycle\nActual:   " + wm.activate());
        System.out.println("\nwm.connect(\"HomeConnect\"):\nExpected: HD-1001 connected to HomeConnect\nActual:   " + wm.connect("HomeConnect"));

        Refrigerator fridge = new Refrigerator(150.0);
        System.out.println("\ngetConsumptionIfTrackable(fridge):\nExpected: 150.0 | Actual: " + SmartHomeController.getConsumptionIfTrackable(fridge));

        MobileApp app = new MobileApp("HomeConnect App");
        System.out.println("\napp.connect(\"HomeConnect\"):\nExpected: HomeConnect App connected to HomeConnect\nActual:   " + app.connect("HomeConnect"));

        HomeDevice ref = wm; // Upcasting WashingMachine to HomeDevice
        System.out.println("\ngetConsumptionIfTrackable(ref):\nExpected: 500.0 | Actual: " + SmartHomeController.getConsumptionIfTrackable(ref));
        System.out.println();
    }
}
