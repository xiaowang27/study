package chapter05;

public class ArrayDetailTwo {
    public static void main(String[] args) {
        // 数组反转
        System.out.println("第一种方法");
        int arr1[] = {1, 2, 3};
        int arr2[] = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr2[arr2.length - i - 1] = arr1[i];
        }
        arr1 = arr2;
        for (int i = 0; i < arr1.length; i++) {
            System.out.printf(arr1[i] + "\t");
        }
        System.out.println();

        // 第二种方法
        System.out.println("第二种方法");
        int arr3[] = {1, 2, 3, 5, 6, 7, 8};
        for (int i = 0; i < arr3.length / 2; i++) {
            int temp = arr3[i];
            arr3[i] = arr3[arr3.length - i - 1];
            arr3[arr3.length - i - 1] = temp;
        }
        for (int i = 0; i < arr3.length; i++) {
            System.out.printf(arr3[i] + "\t");
        }
    }
}
