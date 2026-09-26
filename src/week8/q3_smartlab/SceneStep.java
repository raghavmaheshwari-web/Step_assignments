package week8.q3_smartlab;

/**
 * Functional interface for a step within an automation scene.
 */
@FunctionalInterface
public interface SceneStep {
    boolean apply(Device device);
}
