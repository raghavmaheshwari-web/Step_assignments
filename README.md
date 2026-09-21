# STEP Semester 3 - Assignments

Solutions to the STEP Semester 3 Java Programming Assignments.

## Repository Structure

```
.
├── README.md
└── src/
    ├── week1/                                 # Week 1: 5 Problems
    │   ├── SeatDuplicationChecker.java        # Problem 1: Exam Hall Seat Duplication Checker
    │   ├── TypingAccuracyChecker.java         # Problem 2: Typing Speed Test Accuracy Checker
    │   ├── TrafficSignalStreakAnalyzer.java   # Problem 3: Traffic Signal Streak Analyzer
    │   ├── WarehouseInventoryBalancer.java    # Problem 4: Warehouse Inventory Balancer
    │   ├── MovieReviewWordLengthProfiler.java  # Problem 5: Movie Review Word Length Profiler
    │   └── Week1TestRunner.java               # Week 1 unified test runner
    └── week2/                                 # Week 2: 5 Problems
        ├── AtmPinLengthValidator.java         # Problem 1: ATM PIN Length Validator
        ├── WordReversalEncoder.java           # Problem 2: Word Reversal Encoder
        ├── ProductInventoryCsvParser.java     # Problem 3: Product Inventory CSV Parser
        ├── LibraryIsbnValidator.java          # Problem 4: Library ISBN Normalizer & Validator
        ├── StopWordFrequencyReporter.java     # Problem 5: Stop-Word-Filtered Word Frequency Report
        └── Week2TestRunner.java               # Week 2 unified test runner
```

---

## Week 1 Problems & Method Signatures

1. **The Exam Hall Seat Duplication Checker**
   - Signature: `public static void checkDuplicateSeats(int[] seatNumbers)`
   - Concepts: Arrays, nested loops, conditional logic, input validation, duplicate suppression.
2. **The Typing Speed Test Accuracy Checker**
   - Signature: `public static void checkTypingAccuracy(String original, String typed)`
   - Concepts: String traversal, `charAt()`, ASCII comparisons, percentage calculations, error formatting.
3. **The Traffic Signal Streak Analyzer**
   - Signature: `public static void findLongestStreak(String signalLog)`
   - Concepts: String traversal, character comparison, running maximum tracking, input validation ('R', 'Y', 'G').
4. **The Warehouse Inventory Balancer**
   - Signature: `public static void analyzeInventory(int[] sectionA, int[] sectionB)`
   - Concepts: Arrays, sum accumulation, balance verification, tracking maximum values with 1-based index reporting.
5. **The Movie Review Word Length Profiler**
   - Signature: `public static void classifyWordLengths(String review)`
   - Concepts: String splitting (`split("\\s+")`), regex cleanup, conditional categorization (Short: 1–4, Medium: 5–8, Long: 9+).

---

## Week 2 Problems & Method Signatures

1. **ATM PIN Length Validator**
   - Signature: `public static void checkPinLength(String pin)`
   - Concepts: `length()`, `if / else`, comparison operators without loops.
2. **Word Reversal Encoder**
   - Signature: `public static String reverseEachWord(String sentence)`
   - Concepts: `split(" ")`, `StringBuilder`, loops, word reversal, string joining.
3. **Product Inventory CSV Parser**
   - Signature: `public static void parseInventoryRecord(String csvLine)`
   - Concepts: `split(",")`, array length validation, formatted record output.
4. **Library ISBN Normalizer & Validator**
   - Signatures:
     - `public static String normalizeCode(String raw)`
     - `public static String validateAndFormat(String code)`
   - Concepts: `trim()`, `substring()`, string concatenation, `Character.isLetter()`, `Character.isDigit()`, `StringBuilder`, multi-stage validation.
5. **Stop-Word-Filtered Word Frequency Report**
   - Signature: `public static void printFilteredWordFrequency(String feedback)`
   - Concepts: `toLowerCase()`, `replace()`, `split("\\s+")`, stop-word filtering (`{"the","was","and","a","is","of","in"}`), frequency counting, descending frequency sorting.

---

## How to Compile and Run

### Compile Everything
```bash
javac -d bin src/week1/*.java src/week2/*.java
```

### Run Unified Test Suites
```bash
# Run Week 1 Tests
java -cp bin week1.Week1TestRunner

# Run Week 2 Tests
java -cp bin week2.Week2TestRunner
```

### Run Any Individual Problem
```bash
# Week 2 - Problem 1
java -cp bin week2.AtmPinLengthValidator

# Week 2 - Problem 2
java -cp bin week2.WordReversalEncoder

# Week 2 - Problem 3
java -cp bin week2.ProductInventoryCsvParser

# Week 2 - Problem 4
java -cp bin week2.LibraryIsbnValidator

# Week 2 - Problem 5
java -cp bin week2.StopWordFrequencyReporter
```
