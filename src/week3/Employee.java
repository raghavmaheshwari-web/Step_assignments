package week3;

/**
 * Base Employee class.
 * Designed to remain unmodified while supporting extensibility through inheritance.
 */
public class Employee {
    private String empId;
    private String empName;
    private double salary;

    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = Math.max(0, salary);
    }

    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public double getSalary() {
        return salary;
    }
}
