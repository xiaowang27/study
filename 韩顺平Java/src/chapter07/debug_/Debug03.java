package chapter07.debug_;

import java.util.Arrays;

public class Debug03 {
    public static void main(String[] args) {
        // debug源码
        int arr[] = {13,2,31,4};

        // 排序
        Arrays.sort(arr);
        for(int i : arr){
            System.out.println(i);
        }
    }
}
