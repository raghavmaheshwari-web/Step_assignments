package week3;

/**
 * InternEmployee extends Employee without modifying the base class.
 * Introduces stipendCap and calculates effectiveSalary = min(salary, stipendCap).
 */
public class InternEmployee extends Employee {
    private double stipendCap;

    public InternEmployee(String empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = Math.max(0, stipendCap);
    }

    public double getStipendCap() {
        return stipendCap;
    }

    public double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}
