package chapter06;

/**
 * 递归的使用
 */
public class Recursion01 {
    public static void main(String[] args) {
        Recursion01 r = new Recursion01();
        int factorial = r.factorial(3);
        System.out.println(factorial);
    }

    public void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);
    }

    public int factorial(int i) {
        if (i == 1) {
            return 1;
        } else {
            System.out.println(i);
            return factorial(i - 1) * i;
        }
    }
}
