package chapter07.extend_;

/**
 * 模拟大学生考试情况
 */
public class Graduate {
    public String name;
    public int age;
    private double score;

    public void setScore(double score) {
        this.score = score;
    }

    public void testing() {
        System.out.println("大学生" + name + "正在考大学数学...");
    }

    public void showInfo() {
        System.out.println("大学生" + name + "的大学数学成绩为：" + score);
    }

}
