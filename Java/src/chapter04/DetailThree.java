package chapter04;

import java.util.Scanner;

public class DetailThree {
    public static void main(String[] args) {
        // 统计三个班成绩情况，每班有五个学生，求出各个班的平均分和所有班级的平均分
        Scanner sc = new Scanner(System.in);
        int score1 = 0, score2 = 0, score3 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("请输入" + (i + 1) + "班" + (j + 1) + "号的成绩：");
                switch (i) {
                    case 0:
                        score1 += sc.nextInt();
                        break;
                    case 1:
                        score2 += sc.nextInt();
                        break;
                    case 2:
                        score3 += sc.nextInt();
                        break;
                }
            }
        }
        System.out.println("1班平均分：" + score1 / 5);
        System.out.println("1班平均分：" + score2 / 5);
        System.out.println("1班平均分：" + score3 / 5);
        System.out.println("3个班级总平均分：" + (score1 + score2 + score3) / 15);


        // 统计三个班及格的人数
    }
}
