package chapter04;

import java.util.Scanner;

public class DetailFoul {
    public static void main(String[] args) {
        // 统计三个班及格人数，每班有五个学生
        Scanner sc = new Scanner(System.in);
        int score = 0;
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("请输入" + (i + 1) + "班" + (j + 1) + "号的成绩：");
                if (score > 60) {
                    System.out.println((i + 1) + "班" + (j + 1) + "号及格了！！！");
                    sum += 1;
                }
            }
        }
        System.out.println("总及格人数为:" + sum);
    }
}
