# STEP Semester 3 - Assignments

Comprehensive solutions to the STEP Semester 3 Java Programming, Object-Oriented Programming (OOP), Inheritance, Polymorphism, Abstraction, Interfaces, Object Class Methods, Inner Classes, Composition, System Design, Data Structures, Linked Lists, Stacks, Queues, Graph Search, and Advanced Linked Lists Assignments.

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
    ├── week9/                                 # Week 9: Data Structures (Category A - 5 Problems)
    │   ├── MallFootfallReport.java            # Problem 1: Prefix Sum Range Queries
    │   ├── BudgetFriendlyStreak.java          # Problem 2: Two-Pointer Sliding Window
    │   ├── NetBalancePeriodCounter.java       # Problem 3: Prefix Sum + Hash Map
    │   ├── ExamScoreBandCounter.java          # Problem 4: Binary Search Lower/Upper Bounds
    │   ├── SpiralStockAuditRoute.java         # Problem 5: 2D Grid Clockwise Spiral Traversal
    │   └── Week9TestRunner.java               # Unified Week 9 test suite
    ├── week10/                                # Week 10: Linked Lists (Category A - 5 Problems)
    │   ├── OrderCancellation.java             # Problem 1: Cancelling the k-th Last Order
    │   ├── BrowserHistory.java                # Problem 2: Campus Portal Browser History
    │   ├── TicketCounterMerger.java           # Problem 3: Merging Two Ticket Counters
    │   ├── PalindromeTrainCode.java           # Problem 4: The Palindrome Train Code
    │   ├── PolynomialCalculator.java          # Problem 5: Polynomial Calculator
    │   └── Week10TestRunner.java              # Unified Week 10 test suite
    ├── week11/                                # Week 11: Stacks, Queues & BFS (Category A - 5 Problems)
    │   ├── CanteenPriceStreak.java            # Problem 1: The Canteen Price Streak (Monotonic Stack)
    │   ├── HackathonBannerPrinter.java        # Problem 2: The Hackathon Banner Printer (Two-Stack Decoding)
    │   ├── QuizScoringFormula.java            # Problem 3: The Quiz Scoring Formula (Two-Stack Direct Evaluation)
    │   ├── BusBay.java                        # Problem 4: The Bus Bay Ring (Circular Buffer Deque)
    │   ├── FireDrillExits.java                # Problem 5: The Fire Drill Exits (Multi-Source BFS)
    │   └── Week11TestRunner.java              # Unified Week 11 test suite
    └── week12/                                # Week 12: Advanced Linked Lists & Cache Design (Category A - 5 Problems)
        ├── DeliveryRouteLoopDetector.java     # Problem 1: Delivery Route Loop Detector (Floyd's Algorithm)
        ├── ExamScriptBatches.java             # Problem 2: Exam Script Batches (k-Group In-Place Reversal)
        ├── RecentPagesCache.java              # Problem 3: Recent Pages Cache (O(1) LRU Cache)
        ├── LibraryReturnsByDueDay.java        # Problem 4: Library Returns by Due Day (Stable Merge Sort)
        ├── TokenMirrorCheck.java              # Problem 5: Token Mirror Check (O(1) Palindrome In-Place Restore)
        └── Week12TestRunner.java              # Unified Week 12 test suite
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
   - **Complexity**: $O(N)$ one-time preprocessing, $O(1)$ per query $\rightarrow O(N + Q)$ total time vs. $O(Q \times N)$ naive; $O(N)$ space.
   - **Trade-off Analysis**: Precomputation cost is worth paying when $Q \ge 1$ repeated range queries occur, transforming $O(N)$ linear scans into instant $O(1)$ boundary subtractions.

2. **Problem 2: Longest Budget-Friendly Streak (`BudgetFriendlyStreak.java`)**
   - **Core Concept**: Dynamic Two-Pointer / Sliding Window
   - **Complexity**: $O(N)$ time vs. $O(N^2)$ brute-force check; $O(1)$ auxiliary space.
   - **Trade-off Analysis**: Non-negativity of snack costs ($costs[i] \ge 0$) establishes monotonicity: extending the right pointer never decreases the sum, and advancing the left pointer never increases it. Strictly greater comparison (`>`) preserves earliest start index on ties.

3. **Problem 3: Net-Balance Period Counter (`NetBalancePeriodCounter.java`)**
   - **Core Concept**: Prefix Sum + Frequency Hash Map
   - **Complexity**: $O(N)$ average time vs. $O(N^2)$ brute-force; $O(N)$ space.
   - **Trade-off Analysis**: Because transaction values can be negative (debits), monotonicity is broken and sliding window fails. Maintaining running prefix sum $S$ and querying previous count of $S - k$ accounts for all contiguous sub-periods summing to $k$.

4. **Problem 4: Exam Score Band Counter (`ExamScoreBandCounter.java`)**
   - **Core Concept**: Binary Search (Lower Bound & Upper Bound)
   - **Complexity**: $O(\log N)$ per query vs. $O(N)$ linear scan; $O(1)$ auxiliary space.
   - **Trade-off Analysis**: Standard binary search stops at arbitrary occurrences of duplicates. Using lower bound (first index $\ge low$) and upper bound (first index $> high$) handles repeating marks at band edges in logarithmic time.

5. **Problem 5: Spiral Stock Audit Route (`SpiralStockAuditRoute.java`)**
   - **Core Concept**: 2D Grid Clockwise Spiral Traversal with Boundary Shrinking
   - **Complexity**: $O(m \times n)$ time visiting each warehouse bin cell exactly once; $O(1)$ auxiliary space.
   - **Trade-off Analysis**: Moving four directional boundaries (`top`, `bottom`, `left`, `right`) with guard checks before bottom-row and left-column walks ensures rectangular grids (where $m \neq n$) never revisit single-row or single-column remnants.

---

## Week 10: Linked Lists (Category A)

1. **Problem 1: Cancelling the k-th Last Order (`OrderCancellation.java`)**
   - **Core Concept**: Single-pass Two-Pointer (Lead and Trailing) with Sentinel Dummy Node
   - **Complexity**: $O(N)$ time single pass, $O(1)$ auxiliary space.
   - **Head Removal Without Special Case**: A sentinel dummy node placed before `head` allows a lead pointer moved $k + 1$ steps ahead to position the trailing pointer directly prior to the node targeted for deletion. When removing `head`, `trailing` rests at `dummy`, seamlessly relinking `dummy.next = head.next` without special branching.

2. **Problem 2: Campus Portal Browser History (`BrowserHistory.java`)**
   - **Core Concept**: Doubly Linked List Navigation
   - **Complexity**: $O(1)$ per `visit`, $O(\min(\text{steps}, \text{distance to head/tail}))$ per `back` / `forward`; $O(V)$ space for $V$ active history nodes.
   - **Discarding Forward History**: Setting `current.next = newNode` and `newNode.prev = current` detaches the entire previous forward chain in $O(1)$ time; unreferenced forward nodes are garbage collected automatically.

3. **Problem 3: Merging Two Ticket Counters (`TicketCounterMerger.java`)**
   - **Core Concept**: In-place Sorted Singly Linked List Merge
   - **Complexity**: $O(m + n)$ time, $O(1)$ auxiliary space (single dummy node, existing nodes relinked).
   - **Comparison with Sorting**: Copying both lists into an array and sorting requires $O((m+n)\log(m+n))$ time and $O(m+n)$ auxiliary memory. In-place relinking achieves strictly linear $O(m+n)$ time and $O(1)$ extra memory.

4. **Problem 4: The Palindrome Train Code (`PalindromeTrainCode.java`)**
   - **Core Concept**: In-place Midpoint Reversal, Comparison, and Restoration
   - **Complexity**: $O(N)$ time (midpoint $N/2$, reverse $N/2$, compare $N/2$, restore $N/2$), $O(1)$ auxiliary space.
   - **Comparison with Array Copy**: Array copies or recursive call stacks require $O(N)$ extra memory. In-place reversal verifies the palindrome condition in $O(1)$ space and fully restores the original linked list before returning.

5. **Problem 5: Polynomial Calculator (`PolynomialCalculator.java`)**
   - **Core Concept**: Two-Way Linked List Merge & Standard Mathematical Formatting
   - **Complexity**: $O(m + n)$ time visiting each term once, $O(m + n)$ space for the combined polynomial.
   - **Zero-Term Cancellation & Formatting**: Terms whose coefficients sum to 0 are excluded from the resultant list. Output is formatted cleanly into standard mathematical notation (e.g. `5x^3 + 9x^2 - 3x + 3`).

---

## Week 11: Stacks, Queues & Breadth-First Search (Category A)

1. **Problem 1: The Canteen Price Streak (`CanteenPriceStreak.java`)**
   - **Core Concept**: Monotonic Stack (storing indices in decreasing order of prices)
   - **Complexity**: $O(N)$ time (each index pushed and popped at most once) vs. $O(N^2)$ nested loops; $O(N)$ auxiliary space.
   - **Key Mechanism**: Day indices whose prices are $\le$ today's price are popped since today's higher price shadows them from blocking future streaks.

2. **Problem 2: The Hackathon Banner Printer (`HackathonBannerPrinter.java`)**
   - **Core Concept**: Two-Stack Decompression (`countStack` & `textStack`) without recursion
   - **Complexity**: $O(L)$ time where $L$ is total expanded output length; $O(D + L)$ space where $D$ is bracket nesting depth.
   - **Key Mechanism**: On `[`, current count and context builder are pushed and a fresh builder begins; on `]`, count and previous builder are popped, repeating and appending the inner string.

3. **Problem 3: The Quiz Scoring Formula (`QuizScoringFormula.java`)**
   - **Core Concept**: Dijkstra's Two-Stack Direct Infix Evaluation
   - **Complexity**: $O(N)$ single pass; $O(N)$ space for operand and operator stacks.
   - **Key Mechanism**: Operator precedence and parentheses resolution applied on-the-fly without intermediate postfix string conversion.

4. **Problem 4: The Bus Bay Ring (`BusBay.java`)**
   - **Core Concept**: Circular Double-Ended Queue (Deque) Ring Buffer on Fixed-Size Array
   - **Complexity**: Strict $O(1)$ time for all operations (`insertFront`, `insertLast`, `deleteFront`, `deleteLast`, `getFront`, `getRear`, `isEmpty`, `isFull`); $O(\text{capacity})$ fixed array space.
   - **Key Mechanism**: Modular arithmetic (`(front - 1 + capacity) % capacity` and `(front + size - 1) % capacity`) with zero array element shifts and zero Java Collection classes.

5. **Problem 5: The Fire Drill Exits (`FireDrillExits.java`)**
   - **Core Concept**: Multi-Source Breadth-First Search (BFS) on 2D Floor Plan Grid
   - **Complexity**: $O(R \times C)$ time vs. $O(R^2 \times C^2)$ repeated BFS; $O(R \times C)$ space.
   - **Key Mechanism**: Enqueues all exits simultaneously at distance 0 before advancing outwards; the first time an open room is visited is guaranteed to be its shortest evacuation route.

---

## Week 12: Advanced Linked Lists & Cache Design (Category A)

1. **Problem 1: Delivery Route Loop Detector (`DeliveryRouteLoopDetector.java`)**
   - **Core Concept**: Floyd's Cycle-Finding Algorithm (Tortoise and Hare)
   - **Complexity**: $O(N)$ time, $O(1)$ auxiliary space without HashSet, visited flags, or node modification.
   - **Why Pointers Must Meet**: Once both pointers enter a loop of length $L$, the distance between them decreases by 1 node per iteration (fast moves 2, slow moves 1), guaranteeing they meet in at most $L$ steps. Loop start and length are determined with zero extra memory.

2. **Problem 2: Exam Script Batches (`ExamScriptBatches.java`)**
   - **Core Concept**: $k$-Group In-Place Linked List Reversal
   - **Complexity**: $O(N)$ time, $O(1)$ auxiliary space (avoids $O(N/k)$ recursive call-stack space).
   - **Tail Preservation**: Checks that a full group of $k$ nodes exists prior to reversing; any final group with fewer than $k$ nodes remains intact in original order.

3. **Problem 3: Recent Pages Cache (`RecentPagesCache.java`)**
   - **Core Concept**: $O(1)$ LRU Cache using Custom Doubly Linked List & HashMap
   - **Complexity**: $O(1)$ time for both `get` and `put`; $O(\text{capacity})$ auxiliary space.
   - **Why Singly Linked Lists Fail**: Deleting an arbitrary node in a singly linked list requires scanning from the head to find the predecessor node in $O(N)$ time. A doubly linked list provides direct `prev` pointers for instant $O(1)$ node excision.

4. **Problem 4: Library Returns by Due Day (`LibraryReturnsByDueDay.java`)**
   - **Core Concept**: In-place Stable Merge Sort on Singly Linked List
   - **Complexity**: $O(N \log N)$ time, $O(\log N)$ recursive stack frames; zero array allocations.
   - **Why Merge Sort Beats Quick Sort**: Linked lists can be split and merged in $O(1)$ extra space purely by relinking pointers. Quick Sort requires random-access indexing to select good pivots and suffers poor cache locality on non-contiguous nodes; Merge Sort guarantees $O(N \log N)$ worst-case time and inherent stability (`left.day <= right.day`).

5. **Problem 5: Token Mirror Check (`TokenMirrorCheck.java`)**
   - **Core Concept**: In-place Midpoint Reversal, Palindrome Comparison, and Exact Restoration
   - **Complexity**: $O(N)$ time (~$2N$ operations), $O(1)$ auxiliary space.
   - **Kiosk Memory Constraints**: Copying into an array or stack requires $O(N)$ memory, which violates the strict $O(1)$ constraint of embedded kiosk hardware. Reversing the second half in-place verifies the palindrome condition and leaves the list unaltered upon return.

---

## How to Compile and Run

### Compile All Source Files
```bash
javac -d bin src/week1/*.java src/week2/*.java src/week3/*.java src/week4/*.java src/week5/*.java src/week6/*.java src/week7/*.java src/week8/*.java src/week8/*/*.java src/week9/*.java src/week10/*.java src/week11/*.java src/week12/*.java
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

# Run Week 10 Tests
java -cp bin week10.Week10TestRunner

# Run Week 11 Tests
java -cp bin week11.Week11TestRunner

# Run Week 12 Tests
java -cp bin week12.Week12TestRunner
```
