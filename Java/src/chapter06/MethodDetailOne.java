package chapter06;

import java.util.Scanner;

public class MethodDetailOne {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入数字，判断是奇数还是偶数");
        int i = sc.nextInt();
        MethodDetailOne m = new MethodDetailOne();
        m.aA(i);
        System.out.println("输入行数：");
        int row = sc.nextInt();
        System.out.println("输入列数：");
        int con = sc.nextInt();
        m.PrintRC(row, con);
    }

    public boolean aA(int i) {
        if (i % 2 == 1) {
            System.out.println("奇数");
            return false;
        }
        System.out.println("偶数");
        return true;
    }

    public void PrintRC(int row, int con) {
        System.out.println("打印" + row + "行" + con + "列的*号");
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= con; j++) {
                System.out.printf("*");
            }
            System.out.println();
        }
    }
}
