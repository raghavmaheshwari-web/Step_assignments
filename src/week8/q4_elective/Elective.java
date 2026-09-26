package week8.q4_elective;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Represents an elective course with fixed capacity and FIFO waitlist queue.
 */
public class Elective {
    private final String name;
    private final int credits;
    private final int capacity;
    private final List<Student> enrolledStudents = new ArrayList<>();
    private final Queue<Student> waitlist = new LinkedList<>();

    public Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean enroll(Student student) {
        if (enrolledStudents.contains(student) || waitlist.contains(student)) {
            System.out.println("Enrollment failed: " + student.getName() + " is already enrolled or on the waitlist.");
            return false;
        }

        // Credit limit is checked before seat availability
        if (!student.canTakeCredits(credits)) {
            int attemptedCredits = student.getCurrentCredits() + credits;
            System.out.printf("Enrollment failed: %s would exceed the %s credit limit (%d/%d).\n",
                    student.getName(), student.getCreditPolicy().getName(),
                    attemptedCredits, student.getCreditPolicy().getMaxCredits());
            return false;
        }

        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            student.addCredits(credits);
            System.out.printf("%s enrolled in %s (credits: %d/%d).\n",
                    student.getName(), name, student.getCurrentCredits(), student.getCreditPolicy().getMaxCredits());
            return true;
        } else {
            System.out.println(name + " is full.");
            waitlist.add(student);
            System.out.printf("%s added to waitlist (position %d).\n", student.getName(), waitlist.size());
            return false;
        }
    }

    public boolean drop(Student student) {
        if (!enrolledStudents.contains(student)) {
            System.out.println("Drop failed: " + student.getName() + " is not enrolled in " + name + ".");
            return false;
        }

        enrolledStudents.remove(student);
        student.deductCredits(credits);
        System.out.printf("%s dropped %s (credits: %d/%d).\n",
                student.getName(), name, student.getCurrentCredits(), student.getCreditPolicy().getMaxCredits());

        promoteFromWaitlist();
        return true;
    }

    private void promoteFromWaitlist() {
        while (enrolledStudents.size() < capacity && !waitlist.isEmpty()) {
            Student candidate = waitlist.poll();
            // Credit limit is checked again when waitlisted student is promoted
            if (candidate.canTakeCredits(credits)) {
                enrolledStudents.add(candidate);
                candidate.addCredits(credits);
                System.out.printf("%s promoted from waitlist and enrolled in %s (credits: %d/%d).\n",
                        candidate.getName(), name, candidate.getCurrentCredits(), candidate.getCreditPolicy().getMaxCredits());
                break;
            } else {
                System.out.printf("Promotion skipped: %s exceeds credit limit.\n", candidate.getName());
            }
        }
    }

    public List<Student> getEnrolledStudents() {
        return Collections.unmodifiableList(enrolledStudents);
    }

    public List<Student> getWaitlist() {
        return Collections.unmodifiableList(new ArrayList<>(waitlist));
    }
}
