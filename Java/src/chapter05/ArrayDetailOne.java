package chapter05;

public class ArrayDetailOne {
    public static void main(String[] args) {
        // 打印字母表
        char[] c = new char[26];
        for (int i = 0; i < c.length; i++) {
            c[i] = (char) ('A' + i);
            System.out.printf(c[i] + "\t");
        }
        System.out.println();

        // 求数组最大值
        int num[] = {4, -1, 9, 10, 23};
        int max = num[0], maxIndex = 0;
        for (int i = 0; i < num.length; i++) {
            if (num[i] >= max) {
                max = num[i];
                maxIndex = i;
            }
        }
        System.out.println("数组最大值为：" + max + "它的下标是：" + maxIndex);
    }
}
