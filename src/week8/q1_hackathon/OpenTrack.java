package week8.q1_hackathon;

/**
 * Open track scoring: simple average of idea, execution, and presentation.
 */
public class OpenTrack implements ScoringTrack {
    @Override
    public String getName() {
        return "Open track";
    }

    @Override
    public double calculateScore(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}
