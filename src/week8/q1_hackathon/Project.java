package week8.q1_hackathon;

import java.util.Objects;

/**
 * Represents a project submitted by a team.
 */
public class Project {
    private final String title;
    private Score score;

    public Project(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Project title cannot be empty.");
        }
        this.title = title.trim();
    }

    public String getTitle() {
        return title;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(title, project.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return title;
    }
}
