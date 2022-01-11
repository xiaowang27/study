package chapter07.extend_.improve_;

public class Extends01 {
    public static void main(String[] args) {
        // 创建子类对象并赋初值
        Pupil p = new Pupil();
        p.name="胡图图";
        p.age=8;
        p.setScore(80);

        // 执行方法
        p.testing();
        p.showInfo();


        Graduate g = new Graduate();
        g.name="胡图图";
        g.age=8;
        g.setScore(100);

        g.testing();
        g.showInfo();
    }
}
