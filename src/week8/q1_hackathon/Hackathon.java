package week8.q1_hackathon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Manages hackathon lifecycle: team registration, project submission,
 * judging, and locked results publication.
 */
public class Hackathon {
    public enum State {
        OPEN,
        JUDGING,
        PUBLISHED
    }

    private final String name;
    private State state;
    private final List<Team> teams = new ArrayList<>();
    private final Set<Student> registeredStudents = new HashSet<>();

    public Hackathon(String name) {
        this.name = name;
        this.state = State.OPEN;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public boolean registerTeam(Team team) {
        if (state == State.PUBLISHED) {
            System.out.println("Registration failed: Hackathon results are already published.");
            return false;
        }

        if (team.getMembers().size() < 2 || team.getMembers().size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return false;
        }

        for (Student student : team.getMembers()) {
            if (registeredStudents.contains(student)) {
                System.out.println("Registration failed: Student " + student.getName() + " is already registered in another team.");
                return false;
            }
        }

        teams.add(team);
        registeredStudents.addAll(team.getMembers());
        System.out.printf("Team %s registered (%d members, %s).\n",
                team.getName(), team.getMembers().size(), team.getTrack().getName());
        return true;
    }

    public boolean submitProject(Team team, String projectTitle) {
        if (state == State.PUBLISHED) {
            System.out.println("Submission failed: Results have already been published.");
            return false;
        }
        if (!teams.contains(team)) {
            System.out.println("Submission failed: Team is not registered.");
            return false;
        }
        if (team.getProject() != null) {
            System.out.println("Submission failed: Team has already submitted a project.");
            return false;
        }
        Project project = new Project(projectTitle);
        team.setProject(project);
        System.out.printf("Project '%s' submitted by %s.\n", projectTitle, team.getName());
        return true;
    }

    public boolean scoreProject(String projectTitle, double idea, double execution, double presentation) {
        if (state == State.PUBLISHED) {
            System.out.println("Rescore rejected: Results have already been published.");
            return false;
        }

        Team targetTeam = null;
        for (Team t : teams) {
            if (t.getProject() != null && t.getProject().getTitle().equalsIgnoreCase(projectTitle)) {
                targetTeam = t;
                break;
            }
        }

        if (targetTeam == null) {
            System.out.println("Scoring failed: Project not found.");
            return false;
        }

        double finalScore = targetTeam.getTrack().calculateScore(idea, execution, presentation);
        Score score = new Score(idea, execution, presentation, finalScore);
        targetTeam.getProject().setScore(score);
        this.state = State.JUDGING;

        System.out.printf(Locale.US, "Score recorded for '%s'. Final score: %.2f.\n",
                projectTitle, finalScore);
        return true;
    }

    public void publishResults() {
        this.state = State.PUBLISHED;
        System.out.println("Results published.");
    }
}
