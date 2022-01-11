package chapter03;

public class Shifting {
    public static void main(String[] args) {
        int a = 1 >> 2;   // 算术右移，低位溢出，符号位不变，用符号位补溢出的高位
        int b = -1 >> 2;
        int c = 1 << 2;   // 算术左移，符号位不变，低位补0
        int d = -1 << 2;
        int e = 3 >>> 2;  // 逻辑右移，低位溢出，高位补0
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);

    }
}
