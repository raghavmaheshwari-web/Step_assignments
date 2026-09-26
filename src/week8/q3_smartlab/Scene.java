package week8.q3_smartlab;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an ordered scene targeting capabilities across available devices.
 */
public class Scene {
    private final String name;
    private final List<SceneStep> steps = new ArrayList<>();

    public Scene(String name) {
        this.name = name;
    }

    public void addStep(SceneStep step) {
        steps.add(step);
    }

    public int execute(List<Device> devices) {
        System.out.println("Scene '" + name + "' started.");
        int actionsApplied = 0;
        for (SceneStep step : steps) {
            for (Device device : devices) {
                if (step.apply(device)) {
                    actionsApplied++;
                }
            }
        }
        System.out.println("Scene '" + name + "' completed: " + actionsApplied + " actions applied.");
        return actionsApplied;
    }

    public String getName() {
        return name;
    }
}
