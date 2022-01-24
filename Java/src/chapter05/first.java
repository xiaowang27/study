package chapter05;

import java.util.Scanner;

public class first {
    public static void main(String[] args) {
        int array1[] = new int[5];   // 定义数组的同时定义它的大小
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < array1.length; i++) {
            System.out.println("输入array1[" + i + "]的值：");
            array1[i] = sc.nextInt();
        }
        for (int i = 0; i < array1.length; i++) {
            System.out.println("array1[" + i + "]的值为：");
        }
    }
}
