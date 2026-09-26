package week8.q4_elective;

/**
 * Runner/Demo for Question 4: The Elective Seat Rush
 */
public class ElectiveSystem {
    public static void runDemo() {
        System.out.println("--- Question 4: The Elective Seat Rush ---");
        EnrollmentService service = new EnrollmentService();

        // 1. Elective 'Cloud Computing' is created with 4 credits and a capacity of 2.
        Elective cloudComputing = new Elective("Cloud Computing", 4, 2);

        // 2. Asha (Regular, currently 20 credits) enrolls.
        Student asha = new Student("Asha", StudentType.REGULAR, 20);
        service.enroll(cloudComputing, asha);

        // 3. Ravi (Honors, currently 22 credits) enrolls.
        Student ravi = new Student("Ravi", StudentType.HONORS, 22);
        service.enroll(cloudComputing, ravi);

        // 4. Neha (Exchange, currently 12 credits) enrolls.
        Student neha = new Student("Neha", StudentType.EXCHANGE, 12);
        service.enroll(cloudComputing, neha);

        // 5. Kiran (Regular, currently 22 credits) enrolls.
        Student kiran = new Student("Kiran", StudentType.REGULAR, 22);
        service.enroll(cloudComputing, kiran);

        // 6. Asha drops 'Cloud Computing'.
        service.drop(cloudComputing, asha);

        System.out.println();
    }

    public static void main(String[] args) {
        runDemo();
    }
}
