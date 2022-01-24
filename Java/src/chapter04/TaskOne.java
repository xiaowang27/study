package chapter04;

/**
 * 某人有100,000元，没经过一个路口需要交一次费，规则如下
 * 1) 当现金大于50000时，每次交5%
 * 2) 当现金小于等于50000时，每次交1000
 * 计算此人可以经过多少路口
 */
public class TaskOne {
    public static void main(String[] args) {
        int sum = 0;
        double money = 100000;
        while (money >= 1000) {
            if (money > 50000) {
                money -= money * 0.05;
                sum += 1;
            } else {
                money -= 1000;
                sum += 1;
            }
        }
        System.out.println("共可以经过" + sum + "次路口");
        System.out.println("剩余" + money + "元");
    }
}
