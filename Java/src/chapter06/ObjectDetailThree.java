package chapter06;

public class ObjectDetailThree {
    public static void main(String[] args) {
        Person p1 = new Person("张三", 16);
        Person p2 = new ObjectDetailThree().copyObject(p1);
        System.out.println(p1);
        System.out.println(p2);
    }

    public Person copyObject(Person p) {
        return new Person(p.getName(), p.getAge());
    }
}
