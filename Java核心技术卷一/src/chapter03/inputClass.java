package chapter03;

import java.io.Console;
import java.util.Scanner;

public class inputClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读取一行
        System.out.println("输入你的名字：");
        String name = sc.nextLine();

        // 读取整数
        System.out.println("输入你的年龄：");
        int age = sc.nextInt();

        // 读取浮点数
        System.out.println("输入你的身高：");
        double length = sc.nextDouble();

        System.out.println("姓名："+name+" 年龄："+age+"  身高："+length);

        // 检测输入中是否还有其他单词
        boolean b = sc.hasNext("abc");
        System.out.println(b);

        // Console从控制台读取密码
       // Console cons = System.console();
       // System.out.println("输入用户名：");
       // String username = cons.readLine("Username:");
       // System.out.println("输入密码：");
       // char[] password = cons.readPassword("Password:");

    }
}
