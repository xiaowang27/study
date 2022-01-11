package chapter04;

import java.util.Scanner;

public class BreakDetailOne {
    public static void main(String[] args) {
        // 1-100内的数求和，输出第一次和大于20时的当前数
        int sum = 0;
        for (int i = 1; i < 101; i++) {
            sum += i;
            if (sum >= 20) {
                for (int j = 1; j <= i; j++) {
                    System.out.printf("\t" + j);
                }
                break;
            }
        }
        System.out.println();

        // 实现登陆验证，由三次机会；账号密码输入成功则输出登陆成功，输错则提示还有几次机会
        Scanner sc = new Scanner(System.in);
        String userName;
        String passWord;
        for (int i = 1; i <= 3; i++) {
            System.out.println("输入账号：");
            userName = sc.nextLine();
            System.out.println("输入密码：");
            passWord = sc.nextLine();
            if (userName.equals("jock") && passWord.equals("123")) {
                System.out.println("登陆成功！");
                break;
            } else {
                System.out.println("输入错误，剩余" + (3 - i) + "次机会！");
            }
        }

    }
}
