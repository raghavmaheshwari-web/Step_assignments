package week8.q1_hackathon;

/**
 * Innovation track scoring: idea 50%, execution 30%, presentation 20%.
 */
public class InnovationTrack implements ScoringTrack {
    @Override
    public String getName() {
        return "Innovation track";
    }

    @Override
    public double calculateScore(double idea, double execution, double presentation) {
        return (idea * 0.50) + (execution * 0.30) + (presentation * 0.20);
    }
}
