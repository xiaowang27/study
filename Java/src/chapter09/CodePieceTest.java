package chapter09;

public class CodePieceTest {
    public static void main(String[] args) {
        B b = new B();
    }

}

class A{
    static{
        System.out.println("A的静态代码快");
    }
    {
        System.out.println("A的普通代码快");
    }
    A(){
        System.out.println("A的构造器");
    }
}

class B extends A{
    static{
        System.out.println("B的静态代码快");
    }

    {
        System.out.println("B的普通代码快");
    }
    B(){
        System.out.println("B的构造器");
    }
    /**
     * A的静态代码快
     * B的静态代码快
     * A的普通代码快
     * A的构造方法
     * B的普通代码快
     * B的构造器
     */
}