package chapter07.super_;

public class B extends A {
    public B() {
        System.out.println("new B");
    }

    public void sum() {
        System.out.println("B类的sum方法...");

        // 希望调用父类的cla()方法，有三种方式：
        super.cal();    // super会跳过本类，直接找父类
        this.cal();
        cal();
        // 找cal()方法时，先找本类--->再找父类--->父类的父类--->...，如果都没有则停止(报错)
        // 属性也是如此
    }
    /*
     * 输出：
     * new A
     * new B
     * B类的sum方法...A类的cal方法
     * B类的cal方法...
     * B类的cal方法...
     * */

    public void cal() {
        System.out.println("B类的cal方法...");
    }
}
