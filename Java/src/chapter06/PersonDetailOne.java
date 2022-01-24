package chapter06;

public class PersonDetailOne {
    public static void main(String[] args) {
        Person p1 = new Person("小明", 12);
        Person p2 = p1;
        System.out.println(p2.age); // 12
        p2.name = "小李";
        System.out.println("p1的名字："+p1.name+"   p2的名字"+p2.name);    // 都是小李，因为p1和p2指向同一内存
    }
}
