package week8.q4_elective;

import java.util.Objects;

/**
 * Represents a university student with credit tracking and policy enforcement.
 */
public class Student {
    private final String name;
    private final CreditPolicy creditPolicy;
    private int currentCredits;

    public Student(String name, CreditPolicy creditPolicy, int currentCredits) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        this.name = name.trim();
        this.creditPolicy = Objects.requireNonNull(creditPolicy, "CreditPolicy cannot be null.");
        this.currentCredits = currentCredits;
    }

    public String getName() {
        return name;
    }

    public CreditPolicy getCreditPolicy() {
        return creditPolicy;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public boolean canTakeCredits(int credits) {
        return currentCredits + credits <= creditPolicy.getMaxCredits();
    }

    public void addCredits(int credits) {
        this.currentCredits += credits;
    }

    public void deductCredits(int credits) {
        this.currentCredits -= credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("%s (%s, credits: %d/%d)",
                name, creditPolicy.getName(), currentCredits, creditPolicy.getMaxCredits());
    }
}
