package week3;

/**
 * Problem F1: From Procedural Mess to a Working Library Fine System
 *
 * Demonstrates transition from procedural programming to Object-Oriented Design (OOP)
 * with instance methods representing individual entity behavior and static methods
 * representing collection-level aggregate operations.
 */
public class LibraryFineSystem {

    public static class BookIssue {
        private String title;
        private String borrowerName;
        private int daysOverdue;

        /**
         * Parameterized constructor setting the state of an individual book issue.
         */
        public BookIssue(String title, String borrowerName, int daysOverdue) {
            this.title = title;
            this.borrowerName = borrowerName;
            this.daysOverdue = Math.max(0, daysOverdue);
        }

        public String getTitle() {
            return title;
        }

        public String getBorrowerName() {
            return borrowerName;
        }

        public int getDaysOverdue() {
            return daysOverdue;
        }

        /**
         * Instance Method: Calculates fine amount for THIS specific book issue.
         * Fine rate: Rs 5 per day overdue.
         *
         * Justification for being an instance method:
         * fineAmount() depends directly on the instance's unique state (this.daysOverdue).
         * Every book issue has its own overdue duration and fine calculation.
         */
        public double fineAmount() {
            return (daysOverdue > 0) ? (daysOverdue * 5.0) : 0.0;
        }

        /**
         * Instance Method: Flags if THIS specific book is overdue by more than 14 days.
         */
        public boolean isSeverelyOverdue() {
            return daysOverdue > 14;
        }

        /**
         * Static Method: Computes total fine collected across an array of BookIssue objects.
         *
         * Justification for being static:
         * totalFineCollected does not operate on or represent the state of any single
         * book issue. Instead, it operates on a collection/array of issues as a utility
         * service belonging to the BookIssue class as a whole. It does not require a
         * 'this' reference to an individual book.
         */
        public static double totalFineCollected(BookIssue[] issues) {
            if (issues == null) {
                return 0.0;
            }
            double total = 0.0;
            for (BookIssue issue : issues) {
                if (issue != null) {
                    total += issue.fineAmount();
                }
            }
            return total;
        }
    }

    public static void runDemo() {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "Alice", 18),
            new BookIssue("Effective Java", "Bob", 5),
            new BookIssue("Refactoring", "Charlie", 0),
            new BookIssue("DSA Handbook", "David", 21),
            new BookIssue("Design Patterns", "Eve", 9)
        };

        for (BookIssue issue : issues) {
            String status = issue.isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.println(issue.getTitle() + " - " + issue.getDaysOverdue() + " days - " + status);
        }

        double totalFine = BookIssue.totalFineCollected(issues);
        System.out.println("Total fine collected: Rs " + totalFine);
    }

    public static void main(String[] args) {
        runDemo();
    }
}
