package chapter07.extend_;

/**
 * 讲解继承的本质
 */
public class ExtendsTheory {
    public static void main(String[] args) {
        Son son = new Son();
        /*
        * 依次加载 Object--->GrandPa--->Father--->Son
        * 由上面的顺序分配类中的属性
        * */
        System.out.println(son.name);
        System.out.println(son.age);
        System.out.println(son.hobby);
        /*
        * 按照查找关系来返回信息
        * 1. 先查看子类是否有该属性，并且可以访问，即返回
        * 2. 子类无，则依次往上寻找*/
    }
}

class GrandPa {
    String name = "大头爷爷";
    String hobby = "旅游";
}

class Father extends GrandPa {
    String name = "大头爸爸";
    int age = 39;
}

class Son extends Father {
    String name = "大头儿子";
}