# STEP Semester 3 - Assignments

Comprehensive solutions to the STEP Semester 3 Java Programming, Object-Oriented Programming (OOP), Inheritance, Polymorphism, Abstraction, and Interface Assignments.

## Repository Structure

```
.
├── README.md
└── src/
    ├── week1/                                 # Week 1: Basic Arrays & Strings (5 Problems)
    ├── week2/                                 # Week 2: Built-in Methods & Strings (5 Problems)
    ├── week3/                                 # Week 3: OOP Fundamentals (Category A - 5 Problems)
    ├── week4/                                 # Week 4: Constructors & Java Keywords (Category A - 5 Problems)
    ├── week5/                                 # Week 5: Access Modifiers & Encapsulation (Category A - 5 Problems)
    ├── week6/                                 # Week 6: Inheritance & Polymorphism (Category A - 5 Problems)
    └── week7/                                 # Week 7: Abstraction & Interface (Category A - 5 Problems)
        ├── Shape.java                         # Problem 1: Abstract Shape with overloaded scale()
        ├── CircleShape.java                   # Problem 1: Circle implementation
        ├── SquareShape.java                   # Problem 1: Square implementation
        ├── ShapeAreaPrinter.java              # Problem 1: Polymorphic area printer
        ├── Exportable.java                    # Problem 2: Exportable interface
        ├── ReportGenerator.java               # Problem 2: Unrelated Exportable report
        ├── UserProfile.java                   # Problem 2: Unrelated Exportable user profile
        ├── ExportManager.java                 # Problem 2: Shared static counter & exportAll()
        ├── ServiceableVehicle.java            # Problem 3: Abstract vehicle with private mileage
        ├── Insurable.java                     # Problem 3: Insurance capability interface
        ├── Forklift.java                      # Problem 3: Forklift implementation
        ├── HeavyDutyForklift.java             # Problem 3: Multilevel forklift with extra check
        ├── FleetTracker.java                  # Problem 3: Insurance inspector helper
        ├── Attackable.java                    # Problem 4: Overloaded attack interface
        ├── Defendable.java                    # Problem 4: Defense interface
        ├── GameCharacter.java                 # Problem 4: Abstract character with ID counter
        ├── Warrior.java                       # Problem 4: Warrior implementing Attackable & Defendable
        ├── Trap.java                          # Problem 4: Trap implementing only Defendable
        ├── ArenaBattleSimulator.java          # Problem 4: resolveDefense processor
        ├── HomeDevice.java                    # Problem 5: Abstract device with serial number
        ├── RemoteControllable.java            # Problem 5: Remote control interface
        ├── EnergyTrackable.java               # Problem 5: Energy tracking interface
        ├── WashingMachine.java                # Problem 5: WashingMachine (both interfaces)
        ├── Refrigerator.java                  # Problem 5: Refrigerator (EnergyTrackable only)
        ├── MobileApp.java                     # Problem 5: MobileApp (RemoteControllable only)
        ├── SmartHomeController.java           # Problem 5: connectAll & consumption inspector
        └── Week7TestRunner.java               # Week 7 unified test runner
```

---

## Week 7: Abstraction & Interface (Category A)

1. **Problem 1: Basic Drawing Canvas**
   - Classes: `Shape`, `CircleShape`, `SquareShape`, `ShapeAreaPrinter`
   - Signatures:
     - `public abstract class Shape`
     - `public abstract double calculateArea()`
     - `void scale(double factor)`
     - `void scale(double xFactor, double yFactor)`
     - `static void printArea(Shape s)`
   - Key Concepts: Abstract classes prohibiting direct instantiation (`new Shape()` fails); compile-time polymorphism through overloaded concrete `scale()` methods; polymorphic area computation.

2. **Problem 2: One-Click Data Export**
   - Classes: `Exportable`, `ReportGenerator`, `UserProfile`, `ExportManager`
   - Signatures:
     - `public interface Exportable { String exportData(); }`
     - `static int getTotalExports()`
     - `static void exportAll(Exportable[] items)`
   - Key Concepts: Decoupling completely unrelated classes through a shared interface without an artificial parent class; unified batch export and single shared static export counter.

3. **Problem 3: Fleet Maintenance Tracker**
   - Classes: `ServiceableVehicle`, `Insurable`, `Forklift`, `HeavyDutyForklift`, `FleetTracker`
   - Signatures:
     - `public abstract class ServiceableVehicle`
     - `public interface Insurable { String getInsuranceInfo(); }`
     - `static String getInsuranceIfApplicable(ServiceableVehicle v)`
   - Key Concepts: Encapsulating mileage with validation (`addMileage` rejects negative km); 3-level multilevel inheritance (`ServiceableVehicle` -> `Forklift` -> `HeavyDutyForklift`) appending checks via `super.performMaintenance()`; safe `instanceof` inspection for insurance capability.

4. **Problem 4: Arena Battle Simulator**
   - Classes: `Attackable`, `Defendable`, `GameCharacter`, `Warrior`, `Trap`, `ArenaBattleSimulator`
   - Signatures:
     - `public interface Attackable { String attack(); String attack(String weaponName); }`
     - `public interface Defendable { String defend(); }`
     - `static void resolveDefense(Defendable[] combatants)`
   - Key Concepts: Multiple interface implementation (`Warrior` implements `Attackable` and `Defendable`); compile-time overloaded `attack()` methods; non-character entities (`Trap`) implementing only `Defendable`; polymorphic resolution.

5. **Problem 5: Connected Home Control Panel**
   - Classes: `HomeDevice`, `RemoteControllable`, `EnergyTrackable`, `WashingMachine`, `Refrigerator`, `MobileApp`, `SmartHomeController`
   - Signatures:
     - `public abstract class HomeDevice`
     - `public interface RemoteControllable { String connect(String appId); }`
     - `public interface EnergyTrackable { double getConsumptionWatts(); }`
     - `static void connectAll(RemoteControllable[] items, String appId)`
     - `static double getConsumptionIfTrackable(HomeDevice d)`
   - Key Concepts: Sibling classes (`WashingMachine`, `Refrigerator`) adopting different capabilities under the same abstract parent; standalone classes (`MobileApp`) implementing interfaces without extending the device hierarchy; safe `instanceof` energy inspection.

---

## How to Compile and Run

### Compile All Source Files
```bash
javac -d bin src/week1/*.java src/week2/*.java src/week3/*.java src/week4/*.java src/week5/*.java src/week6/*.java src/week7/*.java
```

### Run Unified Test Suites
```bash
# Run Week 1 Tests
java -cp bin week1.Week1TestRunner

# Run Week 2 Tests
java -cp bin week2.Week2TestRunner

# Run Week 3 Tests
java -cp bin week3.Week3TestRunner

# Run Week 4 Tests
java -cp bin week4.Week4TestRunner

# Run Week 5 Tests
java -cp bin week5.Week5TestRunner

# Run Week 6 Tests
java -cp bin week6.Week6TestRunner

# Run Week 7 Tests
java -cp bin week7.Week7TestRunner
```
