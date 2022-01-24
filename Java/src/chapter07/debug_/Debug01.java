package chapter07.debug_;

public class Debug01 {
    public static void main(String[] args) {
        // 演示逐行执行代码
        int res = 0;
        for (int i = 0; i < 5; i++) {
            res += i;
            System.out.println("i=" + i);
            System.out.println("res=" + res);
        }
        System.out.println("退出for...");
        int test = test(res);   // F8不会跳入方法，F7会
        System.out.println(test);
    }

    public static int test(int sum) {
        System.out.println("进入方法");
        sum = 20;
        return sum;
    }
}
