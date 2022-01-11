package chapter04;

import java.util.Scanner;

public class DoWhileDetailOne {
    public static void main(String[] args) {
        // 打印1-100
        int i = 1;
        do {
            System.out.println(i);
            i++;
        } while (i <= 100);

        // 求1-100的和
        i = 1;
        int sum = 0;
        do {
            sum += i;
            i++;
        } while (i <= 100);
        System.out.println("1-100的和为:" + sum);

        // 统计1-200之间能被5整除但不能被3整除的数的个数
        sum = 0;
        i = 0;
        do {
            if ((i % 5 == 0) && (i % 3 != 0)) {
                sum += 1;
                System.out.println("能被5整除但不能被3整除:" + i);
            }
            i++;
        } while (i <= 200);
        System.out.println("1-200之间能被5整除但不能被3整除的数的个数为" + sum);
    }
}
