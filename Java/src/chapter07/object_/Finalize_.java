package chapter07.object_;

/**
 * finalize方法演示
 */

public class Finalize_ {
    public static void main(String[] args) {
        Car bmw = new Car("宝马");

        bmw = null;
        /*
        * 这时car对象就是一个垃圾，垃圾回收期就会销毁改对象，在销毁该对象前会调用该对象的finalize方法
        * 如果程序员不重写finalize，那么会调用Object类finalize
        * */
        System.gc();    // 并不是说有一个对象变成垃圾后，垃圾回收器就立即执行的。所以此时主动触发垃圾回收机制
        System.out.println("程序退出...");
    }
}

class Car{
    private String name;

    public Car(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this.getClass().getName()+"无引用了，正在执行finalize()方法");
       // super.finalize();
    }
}