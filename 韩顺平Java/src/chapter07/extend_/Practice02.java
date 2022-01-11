package chapter07.extend_;

public class Practice02 {
    public static void main(String[] args) {
        C02 c = new C02();
        /* 我是a类
         * 我是b的有参
         * 我是c的有参
         * 我是c的无参
         * */
    }
}

class A02 {
    A02() {
        System.out.println("我是A02类");
    }
}

class B02 extends A02 {
    B02() {
        System.out.println("我是B02的无参构造");
    }

    B02(String name) {
        System.out.println("我是B02的有参构造");
    }

}

class C02 extends B02 {
    C02() {
        this("Hello");
        System.out.println("我是C02的无参构造");
    }

    C02(String name) {
        super("hahaha");
        System.out.println("我是C02的有参构造");
    }
}
