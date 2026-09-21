# STEP Semester 3 - Assignments

Comprehensive solutions to the STEP Semester 3 Java Programming, Object-Oriented Programming (OOP), and Encapsulation Assignments.

## Repository Structure

```
.
├── README.md
└── src/
    ├── week1/                                 # Week 1: Basic Arrays & Strings (5 Problems)
    ├── week2/                                 # Week 2: Built-in Methods & Strings (5 Problems)
    ├── week3/                                 # Week 3: OOP Fundamentals (Category A - 5 Problems)
    ├── week4/                                 # Week 4: Constructors & Java Keywords (Category A - 5 Problems)
    └── week5/                                 # Week 5: Access Modifiers & Encapsulation (Category A - 5 Problems)
        ├── AccessChecker.java                 # Problems 1 & 2: Visibility rules & context linter
        ├── LibraryMember.java                 # Problem 1: Field encapsulation & ID validation
        ├── BookInventory.java                 # Problem 3: Book copy circulation guard
        ├── LibraryMemberBean.java             # Problem 4: JavaBean, write-once ID, write-only answer
        ├── LoanReceipt.java                   # Problem 5: Immutable receipt with defensive copying
        ├── ReferenceOnlyLoanReceipt.java      # Problem 5: Subclass for reading room loans
        ├── NightlyCirculationLedger.java      # Problem 5: Static ledger and null-safe processor
        └── Week5TestRunner.java               # Week 5 unified test runner
```

---

## Week 5: Access Modifiers & Encapsulation (Category A)

1. **Problem 1: Membership Field Reach Checker**
   - Classes: `AccessChecker`, `LibraryMember`
   - Signatures:
     - `static String classifyAccess(String fieldModifier, String accessorContext)`
     - `static String summarizeByModifier(String[][] attempts)`
     - `public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName)`
   - Key Concepts: Real Java visibility rules across `SAME_CLASS`, `SAME_PACKAGE`, `DIFFERENT_PACKAGE`; constructor-time validation enforcing `membershipId` length >= 4 without no-arg constructor.

2. **Problem 2: Reference Desk Subclass Reach**
   - Class: `AccessChecker`
   - Signatures:
     - `static String classifyAccess(String fieldModifier, String accessorContext)`
     - `static String describeContext(String accessorContext)`
   - Key Concepts: The protected cross-package rule: accessible via own subclass type (`SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE` -> `ALLOWED`), but denied via parent reference type (`SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE` -> `DENIED`). String splitting and title casing.

3. **Problem 3: Book Copy Circulation Guard**
   - Class: `BookInventory`
   - Signatures:
     - `BookInventory(int copiesTotal)`
     - `void checkOut()`
     - `void checkIn()`
     - `int getCopiesAvailable()`
   - Key Concepts: Private state encapsulation, maintaining invariant `0 <= copiesAvailable <= copiesTotal`, constructor validation (`copiesTotal > 0`), safe state transitions.

4. **Problem 4: LibraryMember JavaBean & Write-Once / Write-Only Properties**
   - Class: `LibraryMemberBean`
   - Signatures:
     - `public LibraryMemberBean()`, `public LibraryMemberBean(String name)`, `public LibraryMemberBean(String membershipId, String name)`
     - `String getMembershipId()`, `void setMembershipId(String id)`
     - `boolean isPremiumMember()`, `void setPremiumMember(boolean premium)`
     - `void setSecurityAnswer(String answer)`
   - Key Concepts: 3 chained constructors via `this(...)`, JavaBean naming conventions (`isX()` for booleans), write-once `membershipId` (silently ignores subsequent assignments), and write-only security answer with no getter.

5. **Problem 5: Immutable Loan Receipt & Nightly Circulation Ledger**
   - Classes: `LoanReceipt`, `ReferenceOnlyLoanReceipt`, `NightlyCirculationLedger`
   - Signatures:
     - `public LoanReceipt(String memberId, String[] bookIds)`
     - `String[] getBookIds()`
     - `LoanReceipt withCorrectedBookId(int index, String newId)`
     - `public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber)`
     - `static String processNightlyCirculation(LoanReceipt[] receipts)`
   - Key Concepts: Construction-time format validation ("BK-" + 3 digits), defensive copying on entry and exit, wither pattern, static initialization block, null-safe batch reconciliation using `instanceof`.

---

## How to Compile and Run

### Compile All Source Files
```bash
javac -d bin src/week1/*.java src/week2/*.java src/week3/*.java src/week4/*.java src/week5/*.java
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
```
