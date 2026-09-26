# STEP Semester 3 - Assignments

Comprehensive solutions to the STEP Semester 3 Java Programming, Object-Oriented Programming (OOP), Inheritance, Polymorphism, Abstraction, Interfaces, Object Class Methods, Inner Classes, Composition, and System Design Assignments.

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
    └── week8/                                 # Week 8: Object Methods, Inner Classes & System Design (Category A - 5 Problems)
        ├── q1_hackathon/                      # Problem 1: The Code Sprint Judging Desk
        │   ├── ScoringTrack.java              # Strategy interface for track scoring
        │   ├── InnovationTrack.java           # Weighted scoring strategy (50% / 30% / 20%)
        │   ├── OpenTrack.java                 # Simple average scoring strategy
        │   ├── Student.java                   # Student entity with Object equals/hashCode
        │   ├── Team.java                      # Team entity with size validation (2-4 members)
        │   ├── Score.java                     # Ratings & computed final score
        │   ├── Project.java                   # Project model
        │   ├── Judge.java                     # Judge scoring actor
        │   ├── Hackathon.java                 # Lifecycle: OPEN -> JUDGING -> PUBLISHED
        │   └── HackathonSystem.java           # Problem 1 runner demo
        ├── q2_parcel/                         # Problem 2: The SwiftShip Parcel Tracker
        │   ├── ParcelStatus.java              # FSM statuses & transition rules
        │   ├── ShippingType.java              # Strategy interface for shipping charges
        │   ├── StandardShipping.java          # Base ₹40 + ₹10/kg
        │   ├── ExpressShipping.java           # Base ₹80 + ₹15/kg
        │   ├── FragileShipping.java           # Reuses Standard via composition + ₹50 fee
        │   ├── NotificationChannel.java       # Observer interface
        │   ├── SmsChannel.java                # Concrete SMS observer
        │   ├── EmailChannel.java              # Concrete Email observer
        │   ├── Customer.java                  # Customer model
        │   ├── Parcel.java                    # Parcel state machine & observer registry
        │   ├── ParcelService.java             # Booking and transition service
        │   └── ParcelTrackerSystem.java       # Problem 2 runner demo
        ├── q3_smartlab/                       # Problem 3: The Smart Lab Control Panel
        │   ├── Capability.java                # Capability interface
        │   ├── PowerCapability.java           # Binary power capability (ON/OFF)
        │   ├── BrightnessCapability.java      # Brightness capability (0-100%)
        │   ├── TemperatureCapability.java     # Temperature capability (16-30°C)
        │   ├── Device.java                    # Device holding dynamic capabilities
        │   ├── SceneStep.java                 # Functional step targeting capabilities
        │   ├── Scene.java                     # Ordered execution engine across devices
        │   └── SmartLabSystem.java            # Problem 3 runner demo
        ├── q4_elective/                       # Problem 4: The Elective Seat Rush
        │   ├── CreditPolicy.java              # Interface for student credit policies
        │   ├── StudentType.java               # Regular (24), Honors (28), Exchange (20)
        │   ├── Student.java                   # Student entity with credit tracking
        │   ├── Elective.java                  # Course with capacity & FIFO waitlist
        │   ├── EnrollmentService.java         # Enrollment & drop coordination
        │   └── ElectiveSystem.java            # Problem 4 runner demo
        ├── q5_smartcard/                      # Problem 5: The Campus Canteen Smart Card
        │   ├── PricingPlan.java               # Discount strategy interface
        │   ├── DayScholarPlan.java            # 0% discount
        │   ├── HostellerPlan.java             # 10% discount
        │   ├── StaffPlan.java                 # 20% discount
        │   ├── CardStatus.java                # ACTIVE / BLOCKED status enum
        │   ├── TransactionType.java           # TOP_UP, PURCHASE, REFUND
        │   ├── Transaction.java               # Immutable transaction ledger record
        │   ├── SmartCard.java                 # Ledger invariant & one-time refund
        │   └── SmartCardSystem.java           # Problem 5 runner demo
        └── Week8TestRunner.java               # Unified Week 8 test suite
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

## How to Compile and Run

### Compile All Source Files
```bash
javac -d bin src/week1/*.java src/week2/*.java src/week3/*.java src/week4/*.java src/week5/*.java src/week6/*.java src/week7/*.java src/week8/*.java src/week8/*/*.java
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
```
