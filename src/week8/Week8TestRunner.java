package week8;

import week8.q1_hackathon.HackathonSystem;
import week8.q2_parcel.ParcelTrackerSystem;
import week8.q3_smartlab.SmartLabSystem;
import week8.q4_elective.ElectiveSystem;
import week8.q5_smartcard.SmartCardSystem;

/**
 * Unified Test Runner for Week 8 Assignment:
 * Object Class Methods, Inner Classes, Composition, Polymorphism & System Design.
 */
public class Week8TestRunner {
    public static void main(String[] args) {
        System.out.println("===============================================================================");
        System.out.println("        STEP SEM-3 · Week 8 · Category A System Design Test Suite              ");
        System.out.println("===============================================================================");
        System.out.println();

        // Question 1: The Code Sprint Judging Desk
        HackathonSystem.runDemo();

        // Question 2: The SwiftShip Parcel Tracker
        ParcelTrackerSystem.runDemo();

        // Question 3: The Smart Lab Control Panel
        SmartLabSystem.runDemo();

        // Question 4: The Elective Seat Rush
        ElectiveSystem.runDemo();

        // Question 5: The Campus Canteen Smart Card
        SmartCardSystem.runDemo();

        System.out.println("===============================================================================");
        System.out.println("                     All Week 8 Systems Verified Successfully!                 ");
        System.out.println("===============================================================================");
    }
}
