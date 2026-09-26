package week8.q1_hackathon;

/**
 * Interface defining scoring strategy for hackathon tracks.
 */
public interface ScoringTrack {
    String getName();
    double calculateScore(double idea, double execution, double presentation);
}
