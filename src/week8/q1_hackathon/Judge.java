package week8.q1_hackathon;

/**
 * Represents a judge who scores hackathon projects.
 */
public class Judge {
    private final String name;

    public Judge(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean scoreProject(Hackathon hackathon, String projectTitle, double idea, double execution, double presentation) {
        return hackathon.scoreProject(projectTitle, idea, execution, presentation);
    }
}
