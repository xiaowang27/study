package chapter07.override_;

public class OverrideTest01 {
    public static void main(String[] args) {
        Person person = new Person("张三",18);
        Student student = new Student("李四",20,100,70);
        person.say();
        student.say();
    }
}
