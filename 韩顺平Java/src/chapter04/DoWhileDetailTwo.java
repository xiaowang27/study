package chapter04;

import java.util.Scanner;

public class DoWhileDetailTwo {
    public static void main(String[] args) {
        // 如果张三不还钱，则李四一直输出五连鞭，直到张三说还钱为止
        Scanner sc = new Scanner(System.in);
        String ZhangSanSay = "哈哈";
        do {
            System.out.println("李四说：五连鞭");
            System.out.println("张三说：");
            ZhangSanSay = sc.next();
        } while (!ZhangSanSay.equals("还钱"));
        System.out.println("李四说：好");
    }
}
