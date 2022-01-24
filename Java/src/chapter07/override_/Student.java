package chapter07.override_;

public class Student extends Person{
    private double score;
    public int id;

    public Student(String name, int age, double score, int id) {
        super(name, age);
        this.score = score;
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void say(){
        System.out.println("我的学号是"+this.id+"我叫"+super.getName()+"今年"+super.getAge()+"岁"+"这次的成绩是"+this.score);
    }
}
