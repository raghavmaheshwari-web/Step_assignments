package week3;

/**
 * Problem F4: Designing the Instance/Static Boundary for a Library Membership System
 *
 * Demonstrates the critical distinction between instance state (unique per object)
 * and static state (shared across the entire class), showing the bug caused by
 * static misuse and its proper object-oriented fix.
 */
public class LibraryMemberBoundaryDemo {

    /**
     * BROKEN VERSION:
     * Marking identity fields as static creates a single memory location shared by all instances.
     */
    public static class BrokenLibraryMember {
        public static String name;
        public static String memberId;
        public static int booksIssued;

        /*
         * Why marking these fields static is wrong:
         * 1. 'name': Every library patron has their own individual identity. Marking 'name'
         *    static causes a single class-level variable to store the name. Instantiating
         *    a new member immediately overwrites the name for every previously created member.
         * 2. 'memberId': Member IDs must uniquely distinguish borrowers. With a static ID,
         *    every patron shares the same identity token, destroying audit trails.
         * 3. 'booksIssued': Borrowing limits must be enforced on an individual basis. A static
         *    'booksIssued' aggregates or overwrites book counts globally across all members.
         */
        public BrokenLibraryMember(String memberName, String id, int books) {
            name = memberName;
            memberId = id;
            booksIssued = books;
        }

        public void printName() {
            System.out.println(name);
        }
    }

    /**
     * FIXED VERSION:
     * Properly places member-specific state in instance fields and class-wide state in static fields.
     */
    public static class LibraryMember {
        // Static fields: shared across all members of the library
        public static String libraryName = "City Central Library";
        public static int memberCount = 0;

        // Instance fields: unique to each library member
        private String name;
        private String memberId;
        private int booksIssued;

        public LibraryMember(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = Math.max(0, booksIssued);
            memberCount++;
            // Automatically derive a unique member ID from the running memberCount
            this.memberId = "LM-" + (1000 + memberCount);
        }

        public String getName() {
            return name;
        }

        public String getMemberId() {
            return memberId;
        }

        public int getBooksIssued() {
            return booksIssued;
        }

        /**
         * Instance method to display an individual member's card.
         */
        public void printMemberCard() {
            System.out.println(name + " | " + memberId);
        }

        /**
         * Static method to display the total number of members across the system.
         */
        public static void printTotalMembers() {
            System.out.println("Total members: " + memberCount);
        }
    }

    public static void runDemo() {
        System.out.println("--- Broken version demo ---");
        BrokenLibraryMember m1 = new BrokenLibraryMember("Aditi", "LM-001", 2);
        BrokenLibraryMember m2 = new BrokenLibraryMember("Rohan", "LM-002", 1);

        m1.printName();
        m2.printName();
        System.out.println("(Aditi's data was overwritten — both members now show “Rohan”)");

        System.out.println("\n--- Fixed version demo ---");
        // Reset count for isolated run
        LibraryMember.memberCount = 0;
        LibraryMember member1 = new LibraryMember("Aditi", 2);
        LibraryMember member2 = new LibraryMember("Rohan", 1);

        member1.printMemberCard();
        member2.printMemberCard();
        LibraryMember.printTotalMembers();
    }

    public static void main(String[] args) {
        runDemo();
    }
}
