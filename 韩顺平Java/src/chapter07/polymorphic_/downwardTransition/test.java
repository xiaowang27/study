package chapter07.polymorphic_.downwardTransition;

public class test {
    public static void main(String[] args) {
        A a = new B("a",18);
        a.say();
        // a.eat();    // 报错
        B b = (B) a;
        b.eat();
        b.say();

        // 另一种强转
        ((B) a).eat();

        // B继承至A，建立一个A对象，调用B的方法
        ((B) a).eat();
    }
}
