package chapter07.polymorphic_;

public class Poly01 {
    public static void main(String[] args) {
        Master master = new Master("小李");
        Dog dog = new Dog("大黄");
        Bone bone = new Bone("排骨");

        master.feed(dog,bone);
        // 当要给小猫喂鱼时，有要重新写一个方法

        master.feedExtends(new Cat("小花"),new Food("小黄鱼"));
    }
}
