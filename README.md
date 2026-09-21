# STEP Semester 3 - Assignments

Solutions to the STEP Semester 3 Java Programming Assignments.

## Repository Structure

```
.
├── README.md
└── src/
    └── week1/
        ├── SeatDuplicationChecker.java        # Problem 1: Exam Hall Seat Duplication Checker
        ├── TypingAccuracyChecker.java         # Problem 2: Typing Speed Test Accuracy Checker
        ├── TrafficSignalStreakAnalyzer.java   # Problem 3: Traffic Signal Streak Analyzer
        ├── WarehouseInventoryBalancer.java    # Problem 4: Warehouse Inventory Balancer
        ├── MovieReviewWordLengthProfiler.java  # Problem 5: Movie Review Word Length Profiler
        └── Week1TestRunner.java               # Unified test runner for all 5 problems
```

---

## Week 1: 5 Problems & Method Signatures

### 1. The Exam Hall Seat Duplication Checker
- **Method Signature**: `public static void checkDuplicateSeats(int[] seatNumbers)`
- **Concepts**: Arrays, nested loops, conditional logic, input validation, duplicate suppression.
- **Constraints**: Arrays and loops only (no Collections classes).

### 2. The Typing Speed Test Accuracy Checker
- **Method Signature**: `public static void checkTypingAccuracy(String original, String typed)`
- **Concepts**: String traversal, `charAt()`, ASCII comparisons, percentage calculations, error formatting.

### 3. The Traffic Signal Streak Analyzer
- **Method Signature**: `public static void findLongestStreak(String signalLog)`
- **Concepts**: String traversal, character comparison, running maximum tracking, input validation ('R', 'Y', 'G').

### 4. The Warehouse Inventory Balancer
- **Method Signature**: `public static void analyzeInventory(int[] sectionA, int[] sectionB)`
- **Concepts**: Arrays, sum accumulation, balance verification, tracking maximum values with 1-based index reporting.

### 5. The Movie Review Word Length Profiler
- **Method Signature**: `public static void classifyWordLengths(String review)`
- **Concepts**: String splitting (`split("\\s+")`), regex cleanup, conditional categorization (Short: 1–4, Medium: 5–8, Long: 9+).

---

## How to Compile and Run

### 1. Compile All Files
From the project root directory:
```bash
javac -d bin src/week1/*.java
```

### 2. Run the Unified Test Suite
```bash
java -cp bin week1.Week1TestRunner
```

### 3. Run Any Individual Problem
```bash
# Problem 1
java -cp bin week1.SeatDuplicationChecker

# Problem 2
java -cp bin week1.TypingAccuracyChecker

# Problem 3
java -cp bin week1.TrafficSignalStreakAnalyzer

# Problem 4
java -cp bin week1.WarehouseInventoryBalancer

# Problem 5
java -cp bin week1.MovieReviewWordLengthProfiler
```
