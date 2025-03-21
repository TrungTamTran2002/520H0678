import java.util.Stack;

class Cau2 {

    // (a) Chuyển decimal sang hệ 16
    public static String decimalToHex(int decimal) {
        if (decimal == 0) return "0";

        Stack<Character> stack = new Stack<>();
        String hexChars = "0123456789ABCDEF";

        while (decimal > 0) {
            int remainder = decimal % 16;
            stack.push(hexChars.charAt(remainder)); // Lưu phần dư vào stack
            decimal /= 16;
        }

        StringBuilder hexResult = new StringBuilder();
        while (!stack.isEmpty()) {
            hexResult.append(stack.pop());
        }

        return hexResult.toString();
    }

    // (b) Đảo ngược chuỗi sử dụng Stack
    public static String reverseString(String str) {
        Stack<Character> stack = new Stack<>();

        // Đưa từng ký tự vào Stack
        for (char ch : str.toCharArray()) {
            stack.push(ch);
        }

        // Pop từng ký tự để tạo chuỗi đảo ngược
        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) {
            reversed.append(stack.pop());
        }

        return reversed.toString();
    }

    // (c) Kiểm tra tính hợp lệ của biểu thức có các dấu ( { [. Ví dụ: ({[]}) -> hợp lệ. ({[])} không hợp lệ; ({[]})) không hợp lệ.
    public static boolean isValidExpression(String expr) {
        Stack<Character> stack = new Stack<>();

        for (char ch : expr.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch); // Đưa dấu mở vào Stack
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) return false; // Nếu stack rỗng mà gặp dấu đóng -> sai

                char lastOpen = stack.pop();
                // Kiểm tra xem dấu đóng có khớp với dấu mở không
                if ((ch == ')' && lastOpen != '(') ||
                    (ch == '}' && lastOpen != '{') ||
                    (ch == ']' && lastOpen != '[')) {
                    return false; // Nếu không khớp, biểu thức không hợp lệ
                }
            }
        }

        return stack.isEmpty(); // Nếu còn dấu mở chưa đóng -> không hợp lệ
    }

    public static void main(String[] args) {
        // Test Câu 2a: Chuyển Decimal -> Hex
        int decimalNumber = 245;
        System.out.println("Hex của " + decimalNumber + " là: " + decimalToHex(decimalNumber)); // FF

        // Test Câu 2b: Đảo ngược chuỗi
        String str = "TRAN TRUNG TAM";
        System.out.println("Chuỗi đảo ngược: " + reverseString(str)); // MAT GNURT GNART

        // Test Câu 2c: Kiểm tra biểu thức hợp lệ
        String expr1 = "({[]})";
        String expr2 = "({[])}";
        System.out.println("Biểu thức " + expr1 + " là: " + isValidExpression(expr1)); 
        System.out.println("Biểu thức " + expr2 + " là: " + isValidExpression(expr2)); 
    }
}
