package chapter03;

public class BaseDataClass {
    public static void main(String[] args) {
        // 八大基本数据类型

        // 整形
        byte b = 1;
        short s = 1;
        int i = 1;
        long l = 1l;

        // 浮点型
        float f = 1.0f;
        double d = 1.0;
        double max = Double.POSITIVE_INFINITY;  // 正无限大
        double min = Double.NEGATIVE_INFINITY;  // 负无线大
        double nan = Double.NaN;      // 非数字
        System.out.println("正无限大："+max+"    负无限大："+min+"    非数字"+nan);
        // 字符型
        char c = 'A';

        // 布尔型 取值有true和false
        boolean bo = true;

        // 常量，常量指示基本数据类型，那么基本数据类型的值就不能改变了；指示对象数据类型，那么对象数据类型的地址不可改变，内容可变
        final int AGE_MAX = 100;

        // 枚举类型,java8不支持本地枚举
        // enum SIZE {SMALL,MEDIUM}
    }
}
