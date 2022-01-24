package chapter06;

import java.util.Scanner;

/**
 * 猴子每天吃一半+1个桃子，吃到第10天时，只剩一个了，求最初多少桃子
 */
public class RecursionTask02 {
    public static void main(String[] args) {
        RecursionTask02 rt = new RecursionTask02();
        System.out.println("输入天数");
        int dayNum = new Scanner(System.in).nextInt();
        int test = rt.test(dayNum);
        System.out.println("第" + dayNum + "剩余桃子数量为：" + test);
    }

    public int test(int i) {
        if (i == 1) {
            return 1;
        } else {
            return (test(i - 1) + 1) * 2;
        }
    }
}
