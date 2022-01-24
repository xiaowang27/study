package chapter06;

import java.util.Scanner;

public class RecursionTask01 {
    public static void main(String[] args) {
        RecursionTask01 rt01 = new RecursionTask01();
        System.out.println("想要第几个位置的斐波拉契数：");
        Scanner sc = new Scanner(System.in);
        int factorial = rt01.test(sc.nextInt());
        if (factorial == 0) {
            System.out.println("不存在");
        } else {
            System.out.println(factorial);
        }
    }

    public int test(int i) {
        if (i == 1) {
            return 1;
        } else if (i == 2) {
            return 1;
        } else if (i <= 0) {
            return 0;
        } else {
            return test(i - 1) + test(i - 2);
        }
    }
}
