package chapter02;

/**
 * String与基本数据类型的相互转换
 */
public class StringAndBaseData {
    public static void main(String[] args) {
        int i = 10;
        double d = 100.0;
        boolean b = true;
        String str1 = "123";
        String str2 = "Hello";
        String str3 = "false";

        // 基本数据类型转String
        String str4 = i+""; // 其他基本数据类型也是如此

        // String转基本数据类型
        int i1= Integer.parseInt(str1);
        double d2 = Double.parseDouble(str1);
        boolean b2 = Boolean.parseBoolean(str3);
    }
}
