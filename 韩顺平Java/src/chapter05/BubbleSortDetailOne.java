package chapter05;

/**
 * 冒泡排序
 * 对24，69，80，57，13使用冒泡排序，将其排成一个从小到大的有序数列
 */
public class BubbleSortDetailOne {
    public static void main(String[] args) {
        int num[] = {24, 69, 80, 57, 13};
        int temp;

        System.out.println("冒泡排序");
        for (int i = 0; i < num.length - 1; i++) {
            System.out.println("第" + (i + 1) + "轮");
            for (int j = i + 1; j < num.length; j++) {
                System.out.println(num[i] + "比" + num[j]);
                if (num[i] > num[j]) {
                    temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                }
                for (int k : num) {
                    System.out.printf(k + "\t");
                }
                System.out.println();
            }
        }
        System.out.println("结果");
        for (int k : num) {
            System.out.printf(k + "\t");
        }
    }
}
