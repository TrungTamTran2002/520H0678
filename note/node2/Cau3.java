class Cau3 {

    //a) Tính x mũ n
    public static double power(double x, int n) {
        if (n == 0) return 1; 
        if (n>0) return x*power(x, n-1);
        return 1/power(x, -n); // neue n<0 thif tính 1/(x^-n)
    }

    // b) Viết hàm đệ quy để tính tổng chữ số của một số nguyên dương.
    // Ví dụ: S(1234)=1+2+3+4=10S(1234) = 1 + 2 + 3 + 4 = 10S(1234)=1+2+3+4=10
    public static int sumOfDigits(int n) {
        if (n < 10) return n; // Nếu có 1 chữ số thì trả về nó
        return (n % 10) + sumOfDigits(n / 10); // lấy số cuối + vs tổng số còn lại
    }

    // c) Tính tổng các phần tử dương trong mảng
    public static int sumPositiveNumbers(int[] arr, int index) {
        if (index == arr.length) return 0; // Nếu hết mảng, trả về 0
        if (arr[index] > 0)
            return arr[index] + sumPositiveNumbers(arr, index + 1); // Cộng phần tử dương vào tổng
        return sumPositiveNumbers(arr, index + 1); // Bỏ qua phần tử âm
    }

    // d) In các phần tử dương trong mảng.
    public static void printPositiveNumbers(int[] arr, int index) {
        if (index == arr.length) return; // Nếu hết mảng, dừng
        if (arr[index] > 0) System.out.print(arr[index] + " "); // In phần tử dương
        printPositiveNumbers(arr, index + 1); // Gọi đệ quy với phần còn lại
    }

    public static void main(String[] args) {
        // Tính x mũ n
        double x = 2;
        int n = 5;
        System.out.println(x + "^" + n + " = " + power(x, n)); 

        // sum các số
        int num = 1234;
        System.out.println("Tổng chữ số của " + num + " = " + sumOfDigits(num)); 

        // Tính tổng số dương trong mảng
        int[] arr = {-1, 2, -3, 4, 5};
        System.out.println("Tổng các số dương trong mảng: " + sumPositiveNumbers(arr, 0)); 

        // In số dương 
        System.out.print("Các số dương trong mảng: ");
        printPositiveNumbers(arr, 0);
        System.out.println();
    }
}
