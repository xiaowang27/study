package chapter07.dynamicBinding;

public class Test {
    public static void main(String[] args) {
        A a = new B();

        /*
        * 不注释B类中的sum和sum1方法
        * 输出：
        * 30
        * 20
        *
        * 注释掉B类中的sum和sum1方法
        * System.out.println(a.sum());
        * 1. 先找B类中有无sum方法
        * 2. 找A类中的sum方法
        * 3. A类中的sum方法调用了getI()方法，动态绑定了，所以调用的B类的getI()
        * 4. System.out.println(a.sum());输出：30
        * 5. 属性没有动态绑定，sum1中使用的是A类的i，i=10
        * 6. System.out.println(a.sum1());输出：20
        * */
        System.out.println(a.sum());
        System.out.println(a.sum1());
    }
}
