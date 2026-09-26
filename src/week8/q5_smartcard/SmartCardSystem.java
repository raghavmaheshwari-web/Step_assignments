package week8.q5_smartcard;

/**
 * Runner/Demo for Question 5: The Campus Canteen Smart Card
 */
public class SmartCardSystem {
    public static void runDemo() {
        System.out.println("--- Question 5: The Campus Canteen Smart Card ---");

        // 1. Card 'C-2045' (Hosteller plan) is topped up with ₹500.
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());
        card.topUp(500.00);

        // 2. Holder buys 'Veg Thali' priced at ₹120.
        card.purchase("Veg Thali", 120.00);

        // 3. Holder buys 'Cold Coffee' priced at ₹60.
        card.purchase("Cold Coffee", 60.00);

        // 4. Holder attempts to buy items worth ₹400.
        card.purchase("Combo Meal", 400.00);

        // 5. Canteen refunds the 'Veg Thali' purchase.
        card.refund("Veg Thali");

        // 6. Canteen attempts to refund the 'Veg Thali' purchase again.
        card.refund("Veg Thali");

        // 7. Holder requests a mini-statement.
        card.printMiniStatement();

        System.out.println();
    }

    public static void main(String[] args) {
        runDemo();
    }
}
