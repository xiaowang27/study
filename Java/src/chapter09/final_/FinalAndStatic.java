package chapter09.final_;

public class FinalAndStatic {
    public static void main(String[] args) {
        System.out.println(AA.TEST_NUM_ONE);
        // AA类的静态代码块不会被加载，因为AA类没有加载

        System.out.println(AA.TEST_NUM_TWO);
        // AA类的静态代码块会被加载，因为使用TEST_NUM_TWO使AA类被加载了
    }
}

class AA {
    public final static int TEST_NUM_ONE = 999;
    public static int TEST_NUM_TWO = 999;

    static {
        System.out.println("AA类被加载了");
    }
}
