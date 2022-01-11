package chapter03;

import java.util.Arrays;
import java.util.Random;

/**
 * 用以展示不规则数组
 */
public class IrregularityArrayClass {
    public static void main(String[] args) {
        Random rand = new Random();
        int [][]array1 = new int[4][];
        for(int i = 0;i<4;i++){
            array1[i] = new int[i+1];
            for(int j=0;j<=i;j++){
                array1[i][j]=j;
            }
            System.out.println(Arrays.toString(array1[i]));
        }
        System.out.println(Arrays.deepToString(array1));
    }
}
