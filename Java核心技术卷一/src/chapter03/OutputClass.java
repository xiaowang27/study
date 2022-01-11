package chapter03;

public class OutputClass {
    public static void main(String[] args) {
        float f = 1234567.1234567f;
        System.out.printf("%4.2f",f);   // 1234567.13

        int i = 333;
        System.out.printf("%n % d %n",i);

        // 打印正数和负数的符号
        System.out.printf("+ %d + %n",3);
        System.out.printf("+ %d + %n",-3);
    }
}
