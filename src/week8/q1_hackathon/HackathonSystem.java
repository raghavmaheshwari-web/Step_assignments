package week8.q1_hackathon;

import java.util.Arrays;

/**
 * Runner/Demo for Question 1: The Code Sprint Judging Desk
 */
public class HackathonSystem {
    public static void runDemo() {
        System.out.println("--- Question 1: The Code Sprint Judging Desk ---");
        Hackathon hackathon = new Hackathon("Code Sprint 2026");

        // 1. Team 'ByteBusters' registers with 3 members (Asha, Ravi, Neha) in the Innovation track.
        Team byteBusters = new Team("ByteBusters", new InnovationTrack(),
                Arrays.asList(new Student("Asha"), new Student("Ravi"), new Student("Neha")));
        hackathon.registerTeam(byteBusters);

        // 2. Team 'SoloCoder' registers with 1 member (Kiran) in the Open track.
        Team soloCoder = new Team("SoloCoder", new OpenTrack(),
                Arrays.asList(new Student("Kiran")));
        hackathon.registerTeam(soloCoder);

        // 3. ByteBusters submits project 'SmartAttend'.
        hackathon.submitProject(byteBusters, "SmartAttend");

        // 4. Judge scores 'SmartAttend': idea 8, execution 7, presentation 9.
        Judge judge = new Judge("Chief Judge");
        judge.scoreProject(hackathon, "SmartAttend", 8, 7, 9);

        // 5. Organiser publishes results.
        hackathon.publishResults();

        // 6. Judge attempts to change the idea score of 'SmartAttend' to 10.
        judge.scoreProject(hackathon, "SmartAttend", 10, 7, 9);
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo();
    }
}
