package week4;

/**
 * Week4TestRunner
 *
 * Unified test suite that executes and verifies all 5 Category A problems
 * for the Week 4 STEP Assignment.
 */
public class Week4TestRunner {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("            STEP SEM-3 WEEK 4 ASSIGNMENT TEST SUITE            ");
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
        System.out.println("Problem 1: Ghost Order Validator");
        System.out.println("---------------------------------------------------------------");
        String[][] rawOrders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };
        System.out.print("Expected: Valid: 2 | Rejected: 2\nActual:   ");
        FoodOrder.processBatch(rawOrders);

        System.out.println("\nDelivery status test:");
        FoodOrder order = new FoodOrder("Ravi", "Paneer Butter Masala");
        order.markDelivered();
        order.markDelivered();
        System.out.println();
    }

    private static void testProblem2() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 2: ASAP or Scheduled — Delivery Slot Booking");
        System.out.println("---------------------------------------------------------------");
        DeliverySlot slot1 = new DeliverySlot("ORD101", "13:00-14:00");
        System.out.println("new DeliverySlot(\"ORD101\", \"13:00-14:00\").isPeakHour()");
        System.out.println("Expected: true | Actual: " + slot1.isPeakHour());

        DeliverySlot slot2 = new DeliverySlot("ORD102");
        System.out.println("\nnew DeliverySlot(\"ORD102\").isPeakHour() (defaults to ASAP)");
        System.out.println("Expected: false | Actual: " + slot2.isPeakHour());
        System.out.println();
    }

    private static void testProblem3() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 3: Canteen Trust-Score Ranking Engine");
        System.out.println("---------------------------------------------------------------");
        Canteen[] list = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats") // defaults to trust score 3
        };

        Canteen[] ranked = Canteen.rankCanteens(list);
        System.out.println("Expected: [\"hb1-c\", \"HB2-C\", \"HB3-C\"]");
        System.out.print("Actual:   [");
        for (int i = 0; i < ranked.length; i++) {
            System.out.print("\"" + ranked[i].getCanteenCode() + "\"");
            if (i < ranked.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]\n");
    }

    private static void testProblem4() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 4: Exam-Week Surge Fee Calculator");
        System.out.println("---------------------------------------------------------------");
        SurgeFeeCalculator calc = new SurgeFeeCalculator(1.0);

        System.out.println("orderValue = 500, delayMinutes = 0  -> Expected: Rs 0.0  | Actual: Rs " + calc.calculateSurgeFee(500, 0));
        System.out.println("orderValue = 500, delayMinutes = 1  -> Expected: Rs 5.0  | Actual: Rs " + calc.calculateSurgeFee(500, 1));
        System.out.println("orderValue = 500, delayMinutes = 16 -> Expected: Rs 72.5 | Actual: Rs " + calc.calculateSurgeFee(500, 16));
        System.out.println();
    }

    private static void testProblem5() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem 5: Nightly Multi-Kitchen Reconciliation Engine");
        System.out.println("---------------------------------------------------------------");
        DeliveryAccount[] accounts = {
            new PremiumDeliveryAccount("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };
        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};

        System.out.println("Expected: 2 processed | 1 null skipped | 1 premium | 1 regular | grand total surge fees = Rs 18.75");
        System.out.print("Actual:   ");
        NightlyReconciliationEngine.processBatch(accounts, amounts, delayMinutesArray);
        System.out.println();
    }
}
