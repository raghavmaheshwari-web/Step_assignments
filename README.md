# STEP Semester 3 - Assignments

Comprehensive solutions to the STEP Semester 3 Java Programming, Object-Oriented Programming (OOP), Inheritance, Polymorphism, Abstraction, Interfaces, Object Class Methods, Inner Classes, Composition, System Design, and Data Structures Assignments.

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
    ├── week7/                                 # Week 7: Abstraction & Interface (Category A - 5 Problems)
    ├── week8/                                 # Week 8: Object Methods, Inner Classes & System Design (Category A - 5 Problems)
    │   ├── q1_hackathon/                      # Problem 1: The Code Sprint Judging Desk
    │   ├── q2_parcel/                         # Problem 2: The SwiftShip Parcel Tracker
    │   ├── q3_smartlab/                       # Problem 3: The Smart Lab Control Panel
    │   ├── q4_elective/                       # Problem 4: The Elective Seat Rush
    │   ├── q5_smartcard/                      # Problem 5: The Campus Canteen Smart Card
    │   └── Week8TestRunner.java               # Unified Week 8 test suite
    └── week9/                                 # Week 9: Data Structures (Category A - 5 Problems)
        ├── MallFootfallReport.java            # Problem 1: Prefix Sum Range Queries
        ├── BudgetFriendlyStreak.java          # Problem 2: Two-Pointer Sliding Window
        ├── NetBalancePeriodCounter.java       # Problem 3: Prefix Sum + Hash Map
        ├── ExamScoreBandCounter.java          # Problem 4: Binary Search Lower/Upper Bounds
        ├── SpiralStockAuditRoute.java         # Problem 5: 2D Grid Clockwise Spiral Traversal
        └── Week9TestRunner.java               # Unified Week 9 test suite
```

---

## Week 8: Object Methods, Inner Classes, Composition & System Design (Category A)

1. **Problem 1: The Code Sprint Judging Desk (`q1_hackathon`)**
   - **Key Classes**: `ScoringTrack`, `InnovationTrack`, `OpenTrack`, `Student`, `Team`, `Project`, `Score`, `Judge`, `Hackathon`
   - **Key Concepts**: Strategy pattern for multi-track scoring rules; team size constraints (2 to 4 members); one-team-per-student invariant; hackathon lifecycle state machine (`OPEN` → `JUDGING` → `PUBLISHED`); score locking post-publication (`Rescore rejected: Results have already been published.`).

2. **Problem 2: The SwiftShip Parcel Tracker (`q2_parcel`)**
   - **Key Classes**: `ParcelStatus`, `ShippingType`, `StandardShipping`, `ExpressShipping`, `FragileShipping`, `NotificationChannel`, `SmsChannel`, `EmailChannel`, `Customer`, `Parcel`, `ParcelService`
   - **Key Concepts**: Finite State Machine for parcel transitions (`BOOKED` → `PICKED_UP` → `IN_TRANSIT` → `OUT_FOR_DELIVERY` → `DELIVERED`); status-conditional cancellation (only while `BOOKED`); composition over inheritance in `FragileShipping`; Observer pattern for real-time multi-channel updates.

3. **Problem 3: The Smart Lab Control Panel (`q3_smartlab`)**
   - **Key Classes**: `Capability`, `PowerCapability`, `BrightnessCapability`, `TemperatureCapability`, `Device`, `SceneStep`, `Scene`
   - **Key Concepts**: Component-based capability architecture avoiding class explosion; dynamic runtime addition of capabilities (`Device.addCapability(...)`); encapsulated range validation (0–100% brightness, 16–30°C temperature); scene execution filtering matching devices and skipping non-supporting devices.

4. **Problem 4: The Elective Seat Rush (`q4_elective`)**
   - **Key Classes**: `CreditPolicy`, `StudentType`, `Student`, `Elective`, `EnrollmentService`
   - **Key Concepts**: Pluggable credit policies (`Regular` max 24, `Honors` max 28, `Exchange` max 20); credit limit verification prioritized before seat check; bounded seat capacity; private FIFO waitlist queue; atomic drop-and-promote workflow with re-validation.

5. **Problem 5: The Campus Canteen Smart Card (`q5_smartcard`)**
   - **Key Classes**: `PricingPlan`, `DayScholarPlan`, `HostellerPlan`, `StaffPlan`, `CardStatus`, `TransactionType`, `Transaction`, `SmartCard`
   - **Key Concepts**: Accounting ledger invariant where card balance is strictly derived as the sum of recorded transactions (`balance = sum(transactions)`); plan-based pricing strategies; single-refund guard preventing double refunds; card blocking state; formatted mini-statement generation.

---

## Week 9: Introduction to Data Structures (Category A)

1. **Problem 1: Mall Footfall Range Report (`MallFootfallReport.java`)**
   - **Core Concept**: 1D Prefix Sums
   - **Time Complexity**: $O(N)$ one-time preprocessing, $O(1)$ per query $\rightarrow O(N + Q)$ total time vs. $O(Q \times N)$ naive.
   - **Space Complexity**: $O(N)$ for prefix sum array.
   - **Trade-off Analysis**: Precomputation cost is worth paying when $Q \ge 1$ repeated range queries occur, transforming $O(N)$ linear scans into instant $O(1)$ boundary subtractions.

2. **Problem 2: Longest Budget-Friendly Streak (`BudgetFriendlyStreak.java`)**
   - **Core Concept**: Dynamic Two-Pointer / Sliding Window
   - **Time Complexity**: $O(N)$ vs. $O(N^2)$ brute-force check.
   - **Space Complexity**: $O(1)$ auxiliary space.
   - **Trade-off Analysis**: Non-negativity of snack costs ($costs[i] \ge 0$) establishes monotonicity: extending the right pointer never decreases the sum, and advancing the left pointer never increases it. Strictly greater comparison (`>`) preserves earliest start index on ties.

3. **Problem 3: Net-Balance Period Counter (`NetBalancePeriodCounter.java`)**
   - **Core Concept**: Prefix Sum + Frequency Hash Map
   - **Time Complexity**: $O(N)$ average time vs. $O(N^2)$ brute-force.
   - **Space Complexity**: $O(N)$ to store prefix sum frequencies.
   - **Trade-off Analysis**: Because transaction values can be negative (debits), monotonicity is broken and sliding window fails. Maintaining running prefix sum $S$ and querying previous count of $S - k$ correctly accounts for all contiguous sub-periods summing to $k$.

4. **Problem 4: Exam Score Band Counter (`ExamScoreBandCounter.java`)**
   - **Core Concept**: Binary Search (Lower Bound & Upper Bound)
   - **Time Complexity**: $O(\log N)$ per query vs. $O(N)$ linear scan.
   - **Space Complexity**: $O(1)$ auxiliary space.
   - **Trade-off Analysis**: Standard binary search stops at arbitrary occurrences of duplicates. Using lower bound (first index $\ge low$) and upper bound (first index $> high$) handles repeating marks at band edges in logarithmic time.

5. **Problem 5: Spiral Stock Audit Route (`SpiralStockAuditRoute.java`)**
   - **Core Concept**: 2D Grid Clockwise Spiral Traversal with Boundary Shrinking
   - **Time Complexity**: $O(m \times n)$ visiting each warehouse bin cell exactly once.
   - **Space Complexity**: $O(1)$ auxiliary space (excluding returned traversal list).
   - **Trade-off Analysis**: Moving four directional boundaries (`top`, `bottom`, `left`, `right`) with guard checks before bottom-row and left-column walks ensures rectangular grids (where $m \neq n$) never revisit single-row or single-column remnants.

---

## How to Compile and Run

### Compile All Source Files
```bash
javac -d bin src/week1/*.java src/week2/*.java src/week3/*.java src/week4/*.java src/week5/*.java src/week6/*.java src/week7/*.java src/week8/*.java src/week8/*/*.java src/week9/*.java
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

# Run Week 8 Tests
java -cp bin week8.Week8TestRunner

# Run Week 9 Tests
java -cp bin week9.Week9TestRunner
```
