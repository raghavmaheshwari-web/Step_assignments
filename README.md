# STEP Semester 3 - Assignments

Comprehensive solutions to the STEP Semester 3 Java Programming and Object-Oriented Programming (OOP) Assignments.

## Repository Structure

```
.
├── README.md
└── src/
    ├── week1/                                 # Week 1: Basic Arrays & Strings (5 Problems)
    ├── week2/                                 # Week 2: Built-in Methods & Strings (5 Problems)
    ├── week3/                                 # Week 3: OOP Fundamentals (Category A - 5 Problems)
    └── week4/                                 # Week 4: Constructors & Java Keywords (Category A - 5 Problems)
        ├── FoodOrder.java                     # Problem 1: Ghost Order Validator
        ├── DeliverySlot.java                  # Problem 2: ASAP or Scheduled Slot Booking
        ├── Canteen.java                       # Problem 3: Canteen Trust-Score Ranking Engine
        ├── SurgeFeeCalculator.java            # Problem 4: Exam-Week Surge Fee Calculator
        ├── DeliveryAccount.java               # Problem 5: Base delivery account model
        ├── PremiumDeliveryAccount.java        # Problem 5: Premium delivery account subclass
        ├── NightlyReconciliationEngine.java   # Problem 5: Batch reconciliation processor
        └── Week4TestRunner.java               # Week 4 unified test runner
```

---

## Week 4: Constructors & Java Keywords (Category A)

1. **Problem 1: Ghost Order Validator**
   - Class: `FoodOrder`
   - Signatures:
     - `public FoodOrder(String studentName, String dishName)`
     - `void markDelivered()`
     - `static void processBatch(String[][] rawOrders)`
   - Key Concepts: Parameterized constructor with strict validation, rejecting blank/whitespace input at construction, state tracking preventing duplicate deliveries.

2. **Problem 2: ASAP or Scheduled — Delivery Slot Booking**
   - Class: `DeliverySlot`
   - Signatures:
     - `public DeliverySlot(String orderId, String timeSlot)`
     - `public DeliverySlot(String orderId)` // chains via `this(orderId, DEFAULT_SLOT)`
     - `boolean isPeakHour()`
   - Key Concepts: Constructor overloading with `this(...)` chaining, single typing of constant `"ASAP"`, peak hour identification.

3. **Problem 3: Canteen Trust-Score Ranking Engine**
   - Class: `Canteen`
   - Signatures:
     - `public Canteen(String canteenCode, String canteenName, int trustScore)`
     - `public Canteen(String canteenCode, String canteenName)`
     - `int compareTo(Canteen other)`
     - `static Canteen[] rankCanteens(Canteen[] canteens)`
   - Key Concepts: Resolving shadowing with `this`, signed `compareTo` contract (trust score descending, case-insensitive code tie-breaking), custom $O(n^2)$ sorting implementation.

4. **Problem 4: Exam-Week Surge Fee Calculator**
   - Class: `SurgeFeeCalculator`
   - Signatures:
     - `public SurgeFeeCalculator(double minimumSurgePercent)`
     - `final double calculateSurgeFee(double orderValue, int delayMinutes)`
   - Key Concepts: `final` class, `final` field, and `final` method; O(1) closed-form calculation across 3 tiers (0.5%, 1%, 2%); floor rate applied only for delayed orders (`delayMinutes > 0`).

5. **Problem 5: Nightly Multi-Kitchen Reconciliation Engine**
   - Classes: `DeliveryAccount`, `PremiumDeliveryAccount`, `NightlyReconciliationEngine`
   - Signatures:
     - `public DeliveryAccount(String studentId, double orderValue)`
     - `public DeliveryAccount(String studentId)`
     - `final double calculateSurgeFee(int delayMinutes)`
     - `void processAccount(DeliveryAccount account, double amount, int delayMinutes)`
     - `static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray)`
   - Key Concepts: Static initialization block, `this(...)` constructor chaining, `instanceof` polymorphic settlement, null-safe batch iteration, operations justification for parallel array length integrity.

---

## How to Compile and Run

### Compile All Source Files
```bash
javac -d bin src/week1/*.java src/week2/*.java src/week3/*.java src/week4/*.java
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
```

### Run Any Week 4 Problem Directly
```bash
# Problem 1
java -cp bin week4.FoodOrder

# Problem 2
java -cp bin week4.DeliverySlot

# Problem 3
java -cp bin week4.Canteen

# Problem 4
java -cp bin week4.SurgeFeeCalculator

# Problem 5
java -cp bin week4.NightlyReconciliationEngine
```
