package chapter06;

import java.util.Arrays;

public class ObjectTestTwo {
    public static void main(String[] args) {
        int num1[] = {1, 2, 3};
        int num2[] = {4, 5, 6};
        System.out.println("最初的num1" + Arrays.toString(num1));    // 最初的num1[1, 2, 3]
        System.out.println("最初的num2" + Arrays.toString(num2));    // 最初的num2[4, 5, 6]
        ObjectTestTwo ot = new ObjectTestTwo();

        // 对象交换
        ot.swopOne(num1, num2);
        System.out.println("对象交换后的num1" + Arrays.toString(num1));   // 对象交换后的num1[1, 2, 3]
        System.out.println("对象交换后的num2" + Arrays.toString(num2));   // 对象交换后的num2[4, 5, 6]

        // 对象元素交换
        ot.swopTwo(num1, num2);
        System.out.println("元素交换后的num1" + Arrays.toString(num1));   // 元素交换后的num1[4, 5, 6]
        System.out.println("元素交换后的num2" + Arrays.toString(num2));   // 元素交换后的num2[1, 2, 3]

        // 类中直接交换
        int temp[] = new int[num1.length];
        temp = num1;
        num1 = num2;
        num2 = temp;
        System.out.println("类中直接交换后的num1" + Arrays.toString(num1)); // 类中直接交换后的num1[1, 2, 3]
        System.out.println("类中直接交换后的num2" + Arrays.toString(num2)); // 类中直接交换后的num2[4, 5, 6]
    }

    public void swopOne(int[] n1, int[] n2) {
        int temp[] = n1;
        n1 = n2;
        n2 = temp;
    }

    public void swopTwo(int[] n1, int[] n2) {
        int[] temp = new int[n1.length];
        for (int i = 0; i < n1.length; i++) {
            temp[i] = n1[i];
        }
        for (int i = 0; i < n1.length; i++) {
            n1[i] = n2[i];
        }
        for (int i = 0; i < n1.length; i++) {
            n2[i] = temp[i];
        }
    }
}
