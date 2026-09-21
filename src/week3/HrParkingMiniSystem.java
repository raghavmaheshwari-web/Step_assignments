package week3;

/**
 * Problem F5: Capstone: A Small HR + Parking Allocation Mini-System
 *
 * Demonstrates:
 * 1. Object Composition (objects containing references to other objects).
 * 2. Inheritance and polymorphism (handling Employee subclasses).
 * 3. Null safety in object navigation (handling unassigned parking slots).
 * 4. Static counters tracking overall system records.
 */
public class HrParkingMiniSystem {

    public static class CompanyEmployeeRecord {
        public static int totalRecords = 0;

        private String name;
        private String empId;
        private Employee employee;
        private ParkingSlot slot;

        /**
         * Constructor tying employee identity, salary profile, and parking slot.
         */
        public CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
            this.name = name;
            this.empId = empId;
            this.employee = employee;
            this.slot = slot;
            totalRecords++;
        }

        public String getName() {
            return name;
        }

        public String getEmpId() {
            return empId;
        }

        public Employee getEmployee() {
            return employee;
        }

        public ParkingSlot getSlot() {
            return slot;
        }

        /**
         * Computes the effective pay dynamically based on the employee's concrete type.
         */
        public double getEffectivePay() {
            if (employee instanceof ManagerEmployee) {
                return ((ManagerEmployee) employee).effectiveSalary();
            } else if (employee instanceof InternEmployee) {
                return ((InternEmployee) employee).effectiveSalary();
            } else if (employee != null) {
                return employee.getSalary();
            }
            return 0.0;
        }

        /**
         * Builds a full profile line with null-safe slot checking.
         */
        public String fullProfile() {
            String slotDisplay = (slot != null) ? slot.getSlotNo() : "no parking assigned";
            double pay = getEffectivePay();
            return name + " | Pay: Rs " + pay + " | Slot: " + slotDisplay;
        }
    }

    public static void runDemo() {
        CompanyEmployeeRecord.totalRecords = 0; // Reset counter for clean run

        ParkingSlot slotA1 = new ParkingSlot("A1", 1, 0);
        ParkingSlot slotA2 = new ParkingSlot("A2", 1, 0);

        // 1. Divya - Manager with slot A1
        Employee divyaEmp = new ManagerEmployee("MGR-001", "Divya", 70000, 8000);
        slotA1.allot("DL01AB1001");
        CompanyEmployeeRecord rec1 = new CompanyEmployeeRecord("Divya", "EMP-001", divyaEmp, slotA1);

        // 2. Karan - Plain Employee with slot A2
        Employee karanEmp = new Employee("EMP-002", "Karan", 40000);
        slotA2.allot("DL01CD2002");
        CompanyEmployeeRecord rec2 = new CompanyEmployeeRecord("Karan", "EMP-002", karanEmp, slotA2);

        // 3. Meera - Intern with no parking allotted (null slot)
        Employee meeraEmp = new InternEmployee("INT-003", "Meera", 12000, 10000);
        CompanyEmployeeRecord rec3 = new CompanyEmployeeRecord("Meera", "EMP-003", meeraEmp, null);

        // Print profiles
        System.out.println(rec1.fullProfile());
        System.out.println(rec2.fullProfile());
        System.out.println(rec3.fullProfile());

        // Print static total records count
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }

    public static void main(String[] args) {
        runDemo();
    }
}
