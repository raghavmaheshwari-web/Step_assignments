package week8.q1_hackathon;

import java.util.Objects;

/**
 * Encapsulates ratings and computed final score for a hackathon project.
 */
public class Score {
    private final double idea;
    private final double execution;
    private final double presentation;
    private final double finalScore;

    public Score(double idea, double execution, double presentation, double finalScore) {
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
        this.finalScore = finalScore;
    }

    public double getIdea() {
        return idea;
    }

    public double getExecution() {
        return execution;
    }

    public double getPresentation() {
        return presentation;
    }

    public double getFinalScore() {
        return finalScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Double.compare(score.idea, idea) == 0 &&
               Double.compare(score.execution, execution) == 0 &&
               Double.compare(score.presentation, presentation) == 0 &&
               Double.compare(score.finalScore, finalScore) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idea, execution, presentation, finalScore);
    }

    @Override
    public String toString() {
        return String.format("Score[idea=%.1f, execution=%.1f, presentation=%.1f, final=%.2f]",
                idea, execution, presentation, finalScore);
    }
}
