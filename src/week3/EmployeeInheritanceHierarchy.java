package week3;

/**
 * Problem F2: Extending Employee Without Touching It
 *
 * Demonstrates inheritance (extends), constructor chaining with super(),
 * and dynamic type checking with the instanceof operator.
 */
public class EmployeeInheritanceHierarchy {

    /**
     * Determines and returns the pay for an employee using instanceof to decide
     * which specialized behavior to invoke.
     */
    public static double calculatePay(Employee emp) {
        if (emp instanceof ManagerEmployee) {
            ManagerEmployee mgr = (ManagerEmployee) emp;
            return mgr.effectiveSalary();
        } else if (emp instanceof InternEmployee) {
            InternEmployee intern = (InternEmployee) emp;
            return intern.effectiveSalary();
        } else {
            return emp.getSalary();
        }
    }

    public static void runDemo() {
        Employee plainEmp = new Employee("EMP-101", "Alex", 40000);
        Employee managerEmp = new ManagerEmployee("MGR-201", "Divya", 70000, 8000);
        Employee internEmp = new InternEmployee("INT-301", "Meera", 12000, 10000);

        System.out.println("Plain employee pay: Rs " + calculatePay(plainEmp));
        System.out.println("Manager effective pay: Rs " + calculatePay(managerEmp));
        System.out.println("Intern effective pay: Rs " + calculatePay(internEmp));
    }

    public static void main(String[] args) {
        runDemo();
    }
}
