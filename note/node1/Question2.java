public class Question2 {
    public static int recur(int n, int k) {
        if (k == 0) return 1;
        return n * recur(n, k - 1);
    }

    public static void main(String[] args) {
        System.out.println(recur(5, 3));  // Expected Output: 125 (5^3)
        System.out.println(recur(2, 4));  // Expected Output: 16 (2^4)
        System.out.println(recur(7, 0));  // Expected Output: 1 (Base case)
    }
}
