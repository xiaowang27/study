package chapter07.extend_.improve_;

/**
 * 父类，是pupil和graduate的父类
 */

public class Student {
    // 共有属性
    public String name;
    public int age;
    private double score;

    // 共有的方法
    public void setScore(double score) {
        this.score = score;
    }

    public void showInfo() {
        System.out.println("小学生" + name + "的小学数学成绩为：" + score);
    }
}
