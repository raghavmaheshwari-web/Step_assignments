package week11;

/**
 * Problem 4: The Bus Bay Ring
 *
 * <p>Implements a campus bus bay managed as a circular double-ended queue (deque) backed by a
 * fixed-size primitive integer array used as a ring buffer. Buses can enter or leave from either end.
 *
 * <p><b>Algorithmic Strategy:</b>
 * Uses modular arithmetic to manage a floating circular window within a fixed array:
 * <ul>
 *   <li>{@code front} tracks the index of the first bus in the bay.</li>
 *   <li>{@code size} tracks the current number of buses.</li>
 *   <li>Moving {@code front} backward (on {@code insertFront}):
 *       {@code front = (front - 1 + capacity) % capacity}.</li>
 *   <li>Accessing the rear slot:
 *       {@code (front + size - 1) % capacity}.</li>
 *   <li>Inserting at the rear:
 *       place at index {@code (front + size) % capacity} and increment {@code size}.</li>
 *   <li>All insertions, deletions, and lookups execute strictly in O(1) time without shifting elements.</li>
 * </ul>
 *
 * <p><b>Complexity Analysis:</b>
 * <ul>
 *   <li><b>Time Complexity:</b> O(1) for every method ({@code insertFront}, {@code insertLast},
 *       {@code deleteFront}, {@code deleteLast}, {@code getFront}, {@code getRear}, {@code isEmpty}, {@code isFull}).</li>
 *   <li><b>Space Complexity:</b> O(capacity) fixed array storage; zero Java Collections classes used.</li>
 * </ul>
 */
public class BusBay {

    private final int[] buffer;
    private final int capacity;
    private int front;
    private int size;

    /**
     * Initializes the bus bay with a fixed capacity.
     *
     * @param capacity maximum number of buses the bay can hold
     */
    public BusBay(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Bus bay capacity must be greater than zero: " + capacity);
        }
        this.capacity = capacity;
        this.buffer = new int[capacity];
        this.front = 0;
        this.size = 0;
    }

    /**
     * Inserts a bus at the front of the bay.
     *
     * @param bus bus identifier
     * @return true on success, false if bay is full
     */
    public boolean insertFront(int bus) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + capacity) % capacity;
        buffer[front] = bus;
        size++;
        return true;
    }

    /**
     * Inserts a bus at the rear of the bay.
     *
     * @param bus bus identifier
     * @return true on success, false if bay is full
     */
    public boolean insertLast(int bus) {
        if (isFull()) {
            return false;
        }
        int rearIndex = (front + size) % capacity;
        buffer[rearIndex] = bus;
        size++;
        return true;
    }

    /**
     * Deletes a bus from the front of the bay.
     *
     * @return true on success, false if bay is empty
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    /**
     * Deletes a bus from the rear of the bay.
     *
     * @return true on success, false if bay is empty
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        size--;
        return true;
    }

    /**
     * Returns the bus at the front of the bay without removing it.
     *
     * @return bus identifier, or -1 if bay is empty
     */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return buffer[front];
    }

    /**
     * Returns the bus at the rear of the bay without removing it.
     *
     * @return bus identifier, or -1 if bay is empty
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        int rearIndex = (front + size - 1) % capacity;
        return buffer[rearIndex];
    }

    /**
     * Checks if the bus bay is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the bus bay is full.
     */
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Returns current number of buses in the bay.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns maximum capacity of the bay.
     */
    public int getCapacity() {
        return capacity;
    }
}
