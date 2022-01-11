package chapter03;

public class StringOperate {
    public static void main(String[] args) {
        String s = "Hello";
        // 1. 提取子串
        String s1 = s.substring(0, 2);
        System.out.println("s1"+s1);
        // 2.拼接
        int i = 10;
        String s2 = s+i;
        System.out.println("s2="+s2);
        String s3 = s1+s2;
        System.out.println("s3="+s3);
        String s4 = String.join("---","A","B","C","D");
        System.out.println("s4="+s4);


        // StringBuilder
        StringBuilder sb = new StringBuilder();
        System.out.println("StringBuilder---------------------");
        // 1. 在字符串末尾添加字符串并返回this
        StringBuilder a = sb.append("Hello");   // Hello
        System.out.println(a);
        // 2. 返回字符串长度
        int length = sb.length();
        System.out.println(length); // 5
        // 3. 在字符串末尾添加一个代码单元并放回this
        StringBuilder append = sb.append('!');
        System.out.println(append); // Hello!
        // 4. 将第x个代码单元设置为c
        sb.setCharAt(sb.length()-1,'-');
        System.out.println(sb); // Hello-
        // 5. 在offset位置插入一个字符串并返回this
        StringBuilder world = sb.insert(sb.length() - 1, "World");
        System.out.println(world);  // HelloWorld-
        // 6. 在offset位置插入一个代码单元并返回this
        StringBuilder insert = sb.insert(sb.length() - 1, '-');
        System.out.println(insert); // HelloWorld--
        // 7. 删除偏移量startIndex到endIndex-1的代码单元并返回this
        StringBuilder delete = sb.delete(sb.length() - 3, sb.length() - 1);
        System.out.println(delete); // HelloWorl-
    }
}
