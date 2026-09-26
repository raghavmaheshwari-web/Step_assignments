package week8.q3_smartlab;

import java.util.Arrays;
import java.util.List;

/**
 * Runner/Demo for Question 3: The Smart Lab Control Panel
 */
public class SmartLabSystem {
    public static void runDemo() {
        System.out.println("--- Question 3: The Smart Lab Control Panel ---");

        // 1. Create 'Lab AC' with Power and Temperature, 'Ceiling Lights' with Power and Brightness,
        //    and 'Projector' with Power only.
        Device labAc = new Device("Lab AC", new PowerCapability(), new TemperatureCapability());
        Device ceilingLights = new Device("Ceiling Lights", new PowerCapability(), new BrightnessCapability());
        Device projector = new Device("Projector", new PowerCapability());

        List<Device> labDevices = Arrays.asList(labAc, ceilingLights, projector);

        // 2. Run scene 'Lecture Mode': turn everything ON, set brightness to 40%, set temperature to 24°C.
        Scene lectureMode = new Scene("Lecture Mode");
        lectureMode.addStep(device -> device.setPower(true));
        lectureMode.addStep(device -> device.setBrightness(40));
        lectureMode.addStep(device -> device.setTemperature(24));

        lectureMode.execute(labDevices);

        // 3. Admin sets temperature of 'Lab AC' to 12°C.
        labAc.setTemperature(12);

        // 4. Admin adds Brightness to 'Projector' and sets its brightness to 70%.
        projector.addCapability(new BrightnessCapability());
        projector.setBrightness(70);

        System.out.println();
    }

    public static void main(String[] args) {
        runDemo();
    }
}
