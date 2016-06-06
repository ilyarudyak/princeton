/**
 * Print all powers less than 2^x *in order*
 * 4 = 2^2
 * 8 = 2^3
 * 9 = 3^2
 * 16 = 2^4
 * 16 = 4^2
 * 25 = 5^2
 * ...
 */
public class PerfectPower implements Comparable<PerfectPower> {
    private final long value;
    private final int a;
    private final int b;

    public PerfectPower(int a, int b) {
        this.value = power(a, b);
        this.a = a;
        this.b = b;
    }

    // brute force exponentiation suffices
    public static long power(int a, int b) {
        long pow = 1;
        for (int i = 0; i < b; i++) {
            pow *= a;
        }
        return pow;
    }
    public int compareTo(PerfectPower that) {
        if      (this.value < that.value) return -1;
        else if (this.value > that.value) return +1;
        else                              return  0;
    }

    public String toString() {
        return value + " = " + a + "^" + b;
    }


    public static void main(String[] args) {

        int x = 10;
        int limit = (int) Math.pow(2, x);
        if (args.length == 1) x = Integer.parseInt(args[0]);

        MinPQ<PerfectPower> pq = new MinPQ<>();

        for (int b = 2; b <= x; b++) {
            pq.insert(new PerfectPower(2, b));
        }

        // find smallest power, print out, and update
        while (!pq.isEmpty()) {
            PerfectPower pp = pq.delMin();
            System.out.print(pp + " ");
            PerfectPower pp2 = new PerfectPower(pp.a + 1, pp.b);
            if (pp2.value <= limit) {
                System.out.println(" insert: " + pp2);
                pq.insert(pp2);
            } else {
                System.out.println();
            }
        }
    }
}
















