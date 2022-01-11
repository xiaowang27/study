package chapter01;

/**
 * 演示转义字符
 */
public class ESCClass {
    public static void main(String[] args) {
        // 制表符 \t
        System.out.println("北京上海广州深圳");
        System.out.println("北京\t上海\t广州\t深圳");

        // 换行符
        System.out.println("天青色等烟雨而我在等你");
        System.out.println("天青色等烟雨\n而我在等你");

        // 输出斜杆\，两个\\表示1个\,
        System.out.println("\\");

        // 输出"
        System.out.println("\"");

        // 输出'
        System.out.println("\'");

        // 输出回车，回车和换行是两个不同的概念
        // 1. 输入栏有湖北
        // 2. 按下回车，光标会顶到最前面，就是湖的前面
        // 3. 输出武汉
        System.out.println("好好学习\r武汉");
    }
}
