package chapter05;

import java.util.Scanner;

public class SearchDetail {
    public static void main(String[] args) {
        int[] num = {1, 8, 10, 39, 1000, 1234};
        Scanner sc = new Scanner(System.in);

        // 顺序查找
        System.out.println("输入需要查找的数字：");
        int target = sc.nextInt();
        boolean flag = false;
        for (int i = 0; i < num.length; i++) {
            if (num[i] == target) {
                System.out.println("数组中存在，目标元素下标为：" + i);
                flag = true;
            }
        }
        if (!flag) System.out.println("目标不存在");
    }
}
