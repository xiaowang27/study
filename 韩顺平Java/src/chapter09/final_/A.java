package chapter09.final_;

public class A {
    public final void test01() {
    }

    public final void test01(String s) {
    }    // 能重载
}

final class B {
}

// class C extens B｛｝   // final修饰了B类，B类不能被继承
class C extends A {
    // public void test01(){}   // final修饰了test01()方法，test01方法不能被重写
    final int age = 18;

    public void test02() {
        // age = 19;    // final修饰了属性age，age不可被更改
        final int num = 888;
        // num = 90;    // final修饰了局部变量num，num不可被更改
    }
}


