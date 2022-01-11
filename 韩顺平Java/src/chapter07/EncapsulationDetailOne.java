package chapter07;

import java.util.Scanner;

/**
 * 定义一个类，不能随便查看人的年龄、工资等隐私，并对年龄进行合理的验证
 * 年龄不能超过120
 * 名字长度在2-6个字符
 */
public class EncapsulationDetailOne {
    public String name;
    private int age;
    private double salary;

    public EncapsulationDetailOne(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        Scanner sc  = new Scanner(System.in);
        if(name.length()>6){
            System.out.println("姓名"+name+"长度过长，重新输入:");
            this.name = sc.nextLine();
        }else if(age>120){
            System.out.println(name+"年龄过大，重新输入:");
            this.age = sc.nextInt();
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "员工" +
                "姓名'" + name + '\'' +
                ", 年龄=" + age +
                ", 工资=" + salary;
    }
}
