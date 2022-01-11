package chapter02;

public class FloatDetail {
    public static void main(String[] args) {
        double d1 = 2.7;    // 2.7
        double d2 = 8.7/3;  // 不是2.7，是2.9
        System.out.println(d1);
        System.out.println(d2);

        // 不要对计算记过是小数的进行比较操作
        if(d1==d2) System.out.println("相等");
        // 正确写法
        if(Math.abs(d1-d2)<0.001) System.out.println("相等");

        double d3 = 2.7;
        double d4 = 2.7;
        if(d3==d4) System.out.println("相等");
    }
}
