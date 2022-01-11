package chapter03;

import java.util.Arrays;
import java.util.Random;

public class ArrayClass {
    public static void main(String[] args) {
        int[] a1;
        int a2[];
        a1 = new int[5];
        a2 = new int[10];

        // 声明时初始化
        int a3[] = {1, 2, 3, 4};

        // 传统for循环
        System.out.println("-------------------------------");
        for (int i = 0; i < a3.length; i++) {
            System.out.println(a3[i]);
        }

        // for each
        System.out.println("-------------------------------");
        for (int i : a3) {
            System.out.println(i);
        }

        // 数组拷贝
        int a4[] = new int[2];
        a4 = Arrays.copyOf(a3,2);
        System.out.println("-------------------------------");
        for(int i : a4){
            System.out.println(i);
        }

        // 数组排序
        Random rand = new Random();
        int arr1[] = new int[10];
        for(int i=0;i<10;i++){
            arr1[i] =rand.nextInt(100)+1;
        }
        System.out.println(Arrays.toString(arr1));
        Arrays.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        // Arrays的常用方法
        int[] ints = Arrays.copyOfRange(a3, 0, 6);
        System.out.println(Arrays.toString(ints));

        int i = Arrays.binarySearch(a3, 8);
        System.out.println(i);
    }
}

