package chapter02;

/**
 * 测试类型转换
 */
public class PracticeOne {
    public static void main(String[] args) {
        // 1.
        short s = 12;
        // s = s-9;    // 不能通过编译,因为进行运算后就变成了int型，int型转short需要进行强制类型转换
        s = (short) (s - 9);

        // 2.
        byte b = 10;
        // b= b+11;    // 不能通过编译,因为进行运算后变成了int类型
        b = (byte) (b + 11);

        // 3.
        char c = 'a';
        int i = 16;
        float d = .314f;
        double result = c + 1 + d;

        // 4.
        byte by = 16;
        short sh = 14;
        // short t = sh+b; // 运算后变成了int类型
        int i2 = sh + by;
    }
}
