package chapter09.single_;

public class SingleTon02 {
    public static void main(String[] args) {
        int i = Cat.i;
        System.out.println("Cat被加载，但是cat对象不会被创建");

        System.out.println("当需要获取cat对象时，才会调用构造器创建cat");
        Cat instance = Cat.getInstance();
        System.out.println(instance);
        Cat instanceTest = Cat.getInstance();
        System.out.println(instance);

    }
}

class Cat{
    /**
     * 步骤
     * 1. 将构造器私有化
     * 2. 定义一个static静态属性对象
     * 3. 提供一个public的static方法，可以返回一个单例对象
     */
    private String name;
    private static Cat cat;
    public static int i = 999;

    private Cat(String name) {
        this.name = name;
        System.out.println("构造器被调用...");
    }

    public static Cat getInstance(){
        if(cat==null){
            cat = new Cat("小黄");
        }
        return cat;
    }
}