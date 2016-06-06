import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by ilyarudyak on 6/5/16.
 */
public class MinPQ<Key> implements Iterable<Key> {

    private Key[] pq;                    // store items at indices 1 to N
    private int N;                       // number of items on priority queue
    private Comparator<Key> comparator;  // optional comparator

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param  initCapacity the initial capacity of this priority queue
     */
    public MinPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        N = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public MinPQ() {
        this(1);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     *
     * @param  initCapacity the initial capacity of this priority queue
     * @param  comparator the order to use when comparing keys
     */
    public MinPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        N = 0;
    }

    /**
     * Initializes an empty priority queue using the given comparator.
     *
     * @param  comparator the order to use when comparing keys
     */
    public MinPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    /**
     * Initializes a priority queue from the array of keys.
     * <p>
     * Takes time proportional to the number of keys, using sink-based heap construction.
     *
     * @param  keys the array of keys
     */
    public MinPQ(Key[] keys) {
        N = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < N; i++)
            pq[i+1] = keys[i];
        for (int k = N/2; k >= 1; k--)
            sink(k);
        assert isMinHeap();
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return <tt>true</tt> if this priority queue is empty;
     *         <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return N == 0;
    }

    public Key getByIndex(int index) {
        if (index > N) {
            return null;
        }
        return pq[index];
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return N;
    }

    /**
     * Returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    // helper function to double the size of the heap array
    private void resize(int capacity) {
        assert capacity > N;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /**
     * Adds a new key to this priority queue.
     *
     * @param  x the key to add to this priority queue
     */
    public void insert(Key x) {
        // double size of array if necessary
        if (N == pq.length - 1) resize(2 * pq.length);

        // add x, and percolate it up to maintain heap invariant
        pq[++N] = x;
        swim(N);
        assert isMinHeap();
    }

    /**
     * Removes and returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");

        // store value to return
        Key tmp = pq[1];

        // (1) swap with the last element and reduce N
        exch(1, N);
        pq[N] = null;
        N--;

        // (2) maintain heap property
        sink(1);

        if ((N > 0) && (N == (pq.length - 1) / 4)) resize(pq.length  / 2);

        return tmp;
    }


    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            if (greater(k, 2 * k) || (2 * k < N && greater(k, 2 * k + 1))) {
                if (2 * k < N && greater(2 * k, 2 * k + 1)) {
                    exch(k, 2 * k + 1);
                    k = 2 * k + 1;
                } else {
                    exch(k, 2 * k);
                    k = 2 * k;
                }
            } else {
                break;
            }
        }
    }

    /***************************************************************************
     * Helper functions for compares and swaps.
     ***************************************************************************/
    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        }
        else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // is pq[1..N] a min heap?
    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    // is subtree of pq[1..N] rooted at k a min heap?
    private boolean isMinHeap(int k) {
        if (k > N) return true;
        int left = 2*k, right = 2*k + 1;
        if (left  <= N && greater(k, left))  return false;
        if (right <= N && greater(k, right)) return false;
        return isMinHeap(left) && isMinHeap(right);
    }


    /**
     * Returns an iterator that iterates over the keys on this priority queue
     * in ascending order.
     * <p>
     * The iterator doesn't implement <tt>remove()</tt> since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    public Iterator<Key> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Key> {
        // create a new pq
        private MinPQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            if (comparator == null) copy = new MinPQ<Key>(size());
            else                    copy = new MinPQ<Key>(size(), comparator);
            for (int i = 1; i <= N; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }

    @Override
    public String toString() {
        return "MinPQ{" +
                "pq=" + Arrays.toString(pq) +
                '}';
    }

    /**
     * Unit tests the <tt>MinPQ</tt> data type.
     */
    public static void main(String[] args) throws FileNotFoundException {
        MinPQ<String> pq = new MinPQ<>();
        Scanner in = new Scanner(new File("src/main/resources/tinyPQ.txt"));
        while (in.hasNext()) {
            String item = in.next();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) System.out.print(pq.delMin() + " ");
        }
        System.out.println("(" + pq.size() + " left on pq)");
    }
}
