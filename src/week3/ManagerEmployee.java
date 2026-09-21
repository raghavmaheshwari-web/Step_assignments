package week3;

/**
 * ManagerEmployee extends Employee without modifying the base class.
 * Introduces teamBonus and calculates effectiveSalary = salary + teamBonus.
 */
public class ManagerEmployee extends Employee {
    private double teamBonus;

    public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = Math.max(0, teamBonus);
    }

    public double getTeamBonus() {
        return teamBonus;
    }

    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}
