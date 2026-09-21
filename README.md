# STEP Semester 3 - Assignments

Comprehensive solutions to the STEP Semester 3 Java Programming, Object-Oriented Programming (OOP), Inheritance, and Polymorphism Assignments.

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
    └── week6/                                 # Week 6: Inheritance & Polymorphism (Category A - 5 Problems)
        ├── RaceEntry.java                     # Base class (Problems 1, 3, 5)
        ├── RunnerEntry.java                   # Single inheritance (Problems 1, 3)
        ├── EliteRunnerEntry.java              # Multilevel inheritance (Problem 2)
        ├── RelayTeamEntry.java                # Hierarchical inheritance (Problems 2, 4, 5)
        ├── AnnouncerBoard.java                # Classifier & polymorphic board (Problems 2, 4)
        └── Week6TestRunner.java               # Week 6 unified test runner
```

---

## Week 6: Inheritance & Polymorphism (Category A)

1. **Problem 1: Race Entry Foundation & Batch Bib Validator**
   - Classes: `RaceEntry`, `RunnerEntry`
   - Signatures:
     - `public RaceEntry(String bibNumber, double entryFee)`
     - `public RunnerEntry(String bibNumber, double entryFee, String category)`
     - `void pay(double amount)`
     - `double getBalanceDue()`
     - `static String registerBatch(String[] bibNumbers, double entryFee)`
   - Key Concepts: Single inheritance (`RunnerEntry extends RaceEntry`) forwarding fields via `super(...)`; constructor validation (`bibNumber` length >= 4); batch processing catching `IllegalArgumentException`.

2. **Problem 2: Three Shapes of One Race Family**
   - Classes: `EliteRunnerEntry`, `RelayTeamEntry`, `AnnouncerBoard`
   - Signatures:
     - `public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus)`
     - `public RelayTeamEntry(String bibNumber, double entryFee, int teamSize)`
     - `static String classifyGeneration(RaceEntry entry)`
     - `static double getTotalBalanceDue(RaceEntry[] entries)`
   - Key Concepts: Multilevel inheritance (3 generations deep) vs. hierarchical inheritance (independent sibling branch); `instanceof`-based generation classification; polymorphic total calculation without type checks.

3. **Problem 3: The Late-Withdrawal Penalty Override & Audit Trail**
   - Classes: `RaceEntry`, `RunnerEntry`
   - Signatures:
     - `protected void applyLateFee(double amount)`
     - `double[] getLateFeeHistory()`
   - Key Concepts: Polymorphic method overriding (`@Override`) doubling penalties via `super.applyLateFee(amount * 2)`; defensive copy returned from `getLateFeeHistory()` protecting private audit history from external tampering.

4. **Problem 4: The Race-Day Announcer Board**
   - Class: `AnnouncerBoard`
   - Signature:
     - `static String announceAll(RaceEntry[] entries)`
   - Key Concepts: Polymorphic dispatch calling `announce()` across mixed arrays; report construction using `StringBuilder`; safe downcasting to `RelayTeamEntry` guarded by `instanceof` to access team size (`[Team size via downcast: 4]`).

5. **Problem 5: Race-Wide Bib Issuance, Discount Codes & Nightly Settlement**
   - Class: `RaceEntry`
   - Signatures:
     - `void pay(double amount, String mode)` (overloaded)
     - `static boolean isValidDiscountCode(String code)`
     - `static int getBibCounter()`
     - `static String settleNight(RaceEntry[] entries)`
   - Key Concepts: Final auto-incrementing `entryCode` driven by a static `bibCounter` incremented inside base constructor; discount code validation (`"M"` + 3 digits + 1 uppercase letter) without regex; null-safe nightly batch settlement.

---

## How to Compile and Run

### Compile All Source Files
```bash
javac -d bin src/week1/*.java src/week2/*.java src/week3/*.java src/week4/*.java src/week5/*.java src/week6/*.java
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
```
