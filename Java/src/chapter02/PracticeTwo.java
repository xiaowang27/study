package chapter02;

public class PracticeTwo {
    public static void main(String[] args) {
        // 2. 使用char，分别保存\n \t \r \\ 1  2  3等字符并输出
        char c1 = '\n';
        char c2 = '\t';
        char c3 = '\r';
        char c4 = '\\';
        char c5 = '1';

        // 3. 保存两本数名，使用+拼接
        String bookName1 = "三国演义";
        String bookName2 = "红楼梦";
        System.out.println(bookName1+bookName2);

        // 4. 使用制表符
        System.out.println("姓名  \t年龄\t成绩  \t性别  \t爱好  \t");
        String name = "张三";
        int age = 18;
        double score = 90.0;
        char gender = '男';
        String hobby = "上法庭";
        System.out.println(name+"    \t"+age+"\t"+score+"\t"+gender+"  \t"+hobby);
    }
}
