package chapter04;

public class ObjectTest {
    public static void main(String[] args) {
        Employee employee1 = new Employee("张三", 15); // Employee{name='张三', age=15}
        Employee employee2 = new Employee("李四", 16); // Employee{name='李四', age=16}
        System.out.println(employee1);
        System.out.println(employee2);

        System.out.println("交换");
        ObjectTest ot = new ObjectTest();
        ot.swop(employee1, employee2);
        System.out.println(employee1);  // Employee{name='张三', age=15}
        System.out.println(employee2);  // Employee{name='李四', age=16}
    }

    public void swop(Employee e1, Employee e2) {
        Employee temp = e1;
        e1 = e2;
        e2 = temp;
    }
}
