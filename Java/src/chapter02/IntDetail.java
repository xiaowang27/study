package chapter02;

/**
 * 整数类型的细节
 * 1. Java各整数类型有固定的范围和长度，不受OS的影响
 * 2. Java整型常量默认为int，声明long型常量后面必须加L
 * 3. Java中除非int不足以表示，菜使用long
 * bit是计算机中最小的存储单位，byte是计算机中最基本的存储单位，1byte=8bit
 */
public class IntDetail {
    public static void main(String[] args) {
        int i1 = 1;
        // int i2=1L; // 报错
        int i3 = 2;
        long l1 = 1L;
        i1 = (int) l1;   //强制类型转换
        l1 = i3;
    }
}
