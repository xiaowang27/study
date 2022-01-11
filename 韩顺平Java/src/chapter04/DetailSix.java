package chapter04;

import java.util.Scanner;

public class DetailSix {
    public static void main(String[] args) {
        // 有多少行，打印多少*
        Scanner sc = new Scanner(System.in);
        int cow = 1;
        System.out.println("需要打印几行：");
        int target = sc.nextInt();
        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= cow; j++) {
                System.out.printf("*");
            }
            cow += 2;
            System.out.println();
        }

        // 打印金字塔
        System.out.println("打印金字塔");
        cow = 1;
        for (int i = 1; i <= target; i++) {
            for (int k = 0; k <= (target - i); k++) {
                System.out.printf(" ");
            }
            for (int j = 1; j <= cow; j++) {
                System.out.printf("*");
            }
            cow += 2;
            System.out.println();
        }

        // 打印空心金字塔
        System.out.println("打印空心金字塔");
        cow = 1;
        int n = 0;
        for (int i = 1; i <= target; i++) {
            for (int k = 0; k <= (target - i); k++) {
                System.out.printf(" ");
            }
            for (int j = 1; j <= cow; j++) {
                if (j == 1 || j == cow || i == target) {
                    System.out.printf("*");
                } else {
                    System.out.print(" ");
                }
            }
            cow += 2;
            System.out.println();
        }
    }
}
