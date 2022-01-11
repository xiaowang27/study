package chapter07;

import java.util.Scanner;

public class EncapsulationDetailOneTest {
    public static void main(String[] args) {
        EncapsulationDetailOne e1 = new EncapsulationDetailOne("张三",20,10000);
        System.out.println(e1);
        EncapsulationDetailOne e2 = new EncapsulationDetailOne("张三三三三三三",20,10000);
        System.out.println(e2);
        EncapsulationDetailOne e3 = new EncapsulationDetailOne("李四",200,10000);
        System.out.println(e3);
        EncapsulationDetailOne e4 = new EncapsulationDetailOne("李四四四四四四",200,10000);
        System.out.println(e4);

        System.out.println("对张三涨工资：");
        System.out.println("张三目前工资为"+e1.getSalary());
        System.out.println("涨多少工资：");
        e1.setSalary(e1.getSalary()+(new Scanner(System.in).nextInt()));
        System.out.println("现在"+e1.getName()+"的工资为："+e1.getSalary());
    }
}
