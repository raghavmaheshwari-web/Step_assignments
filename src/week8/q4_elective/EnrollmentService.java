package week8.q4_elective;

/**
 * Service managing student course enrollment and drops.
 */
public class EnrollmentService {
    public boolean enroll(Elective elective, Student student) {
        return elective.enroll(student);
    }

    public boolean drop(Elective elective, Student student) {
        return elective.drop(student);
    }
}
