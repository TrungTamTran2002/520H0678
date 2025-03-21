import java.util.Stack;

public class Question3 {
    public static int calculate(String[] expression) {
        Stack<Integer> stack = new Stack<>();

        for (String token : expression) {
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                if (stack.size() < 2) throw new IllegalArgumentException("Invalid expression");
                int b = stack.pop();
                int a = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported operator: " + token);
                }
            }
        }
        return stack.pop();
    }

    public static boolean isNumber(String str) {
        return str.matches("0|([1-9][0-9]*)");
    }

    public static void main(String[] args) {
        System.out.println(calculate(new String[]{"3", "4", "+", "2", "1", "+", "-"})); // Expected Output: 4
        System.out.println(calculate(new String[]{"31", "12", "+"})); // Expected Output: 43
        System.out.println(calculate(new String[]{"5", "3", "-"})); // Expected Output: 2
    }
}
