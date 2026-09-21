# STEP Semester 3 - Assignments

Comprehensive solutions to the STEP Semester 3 Java Programming and OOP Assignments.

## Repository Structure

```
.
├── README.md
└── src/
    ├── week1/                                 # Week 1: Basic Arrays & Strings (5 Problems)
    │   ├── SeatDuplicationChecker.java        # Problem 1: Exam Hall Seat Duplication Checker
    │   ├── TypingAccuracyChecker.java         # Problem 2: Typing Speed Test Accuracy Checker
    │   ├── TrafficSignalStreakAnalyzer.java   # Problem 3: Traffic Signal Streak Analyzer
    │   ├── WarehouseInventoryBalancer.java    # Problem 4: Warehouse Inventory Balancer
    │   ├── MovieReviewWordLengthProfiler.java  # Problem 5: Movie Review Word Length Profiler
    │   └── Week1TestRunner.java               # Week 1 test runner
    ├── week2/                                 # Week 2: Built-in Methods & Strings (5 Problems)
    │   ├── AtmPinLengthValidator.java         # Problem 1: ATM PIN Length Validator
    │   ├── WordReversalEncoder.java           # Problem 2: Word Reversal Encoder
    │   ├── ProductInventoryCsvParser.java     # Problem 3: Product Inventory CSV Parser
    │   ├── LibraryIsbnValidator.java          # Problem 4: Library ISBN Normalizer & Validator
    │   ├── StopWordFrequencyReporter.java     # Problem 5: Stop-Word-Filtered Word Frequency Report
    │   └── Week2TestRunner.java               # Week 2 test runner
    └── week3/                                 # Week 3: OOP Fundamentals (Category A - 5 Problems)
        ├── Employee.java                      # Base Employee class (F2, F5)
        ├── ManagerEmployee.java               # ManagerEmployee with bonus (F2, F5)
        ├── InternEmployee.java                # InternEmployee with stipend cap (F2, F5)
        ├── ParkingSlot.java                   # ParkingSlot model with allotment (F3, F5)
        ├── LibraryFineSystem.java             # Problem F1: Library Fine System
        ├── EmployeeInheritanceHierarchy.java  # Problem F2: Inheritance & instanceof dispatch
        ├── ParkingSlotAllocationSystem.java   # Problem F3: References & null safety
        ├── LibraryMemberBoundaryDemo.java     # Problem F4: Instance vs static boundary bug & fix
        ├── HrParkingMiniSystem.java           # Problem F5: Capstone HR + Parking composition
        └── Week3TestRunner.java               # Week 3 test runner
```

---

## Week 3: OOP Fundamentals (Category A)

1. **Problem F1: Library Fine System**
   - Class: `BookIssue`
   - Methods: `double fineAmount()`, `boolean isSeverelyOverdue()`, `static double totalFineCollected(BookIssue[] issues)`
   - Highlights: Demonstrates why `fineAmount()` is an instance method (depends on individual `daysOverdue` state) whereas `totalFineCollected()` is static (operates on collections as a utility service).

2. **Problem F2: Extending Employee Without Touching It**
   - Classes: `Employee`, `ManagerEmployee`, `InternEmployee`
   - Highlights: Demonstrates Open-Closed Principle using inheritance (`extends`), constructor chaining with `super()`, and dynamic dispatch with `instanceof`.

3. **Problem F3: Object References, Null Safety, and a Mutating Method**
   - Class: `ParkingSlot`, methods `findAvailableSlot()`, `safeAllot()`
   - Highlights: Explains Java pass-by-value reference semantics (why arrays are not cloned) and defensive null checks eliminating `NullPointerException`.

4. **Problem F4: Designing the Instance/Static Boundary**
   - Classes: `BrokenLibraryMember` vs `LibraryMember`
   - Highlights: Reproduces real-world bug where marking fields static overwrites individual member identities; demonstrates the proper fix with auto-generated IDs (`LM-1001`, `LM-1002`).

5. **Problem F5: Capstone Mini-System (HR + Parking Allocation)**
   - Class: `CompanyEmployeeRecord`
   - Highlights: Object composition (objects holding object references), polymorphic pay calculation, null-safe parking slot display, and static record counter.

---

## How to Compile and Run

### Compile All Source Files
```bash
javac -d bin src/week1/*.java src/week2/*.java src/week3/*.java
```

### Run Unified Test Suites
```bash
# Run Week 1 Tests
java -cp bin week1.Week1TestRunner

# Run Week 2 Tests
java -cp bin week2.Week2TestRunner

# Run Week 3 Tests
java -cp bin week3.Week3TestRunner
```

### Run Any Week 3 Problem Directly
```bash
# Problem F1
java -cp bin week3.LibraryFineSystem

# Problem F2
java -cp bin week3.EmployeeInheritanceHierarchy

# Problem F3
java -cp bin week3.ParkingSlotAllocationSystem

# Problem F4
java -cp bin week3.LibraryMemberBoundaryDemo

# Problem F5
java -cp bin week3.HrParkingMiniSystem
```
