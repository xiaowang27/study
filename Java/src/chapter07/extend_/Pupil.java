package chapter07.extend_;

/**
 * 模拟小学生考试情况
 */
public class Pupil {
    public String name;
    public int age;
    private double score;

    public void setScore(double score) {
        this.score = score;
    }

    public void testing() {
        System.out.println("小学生" + name + "正在考小学数学...");
    }

    public void showInfo() {
        System.out.println("小学生" + name + "的小学数学成绩为：" + score);
    }
}
