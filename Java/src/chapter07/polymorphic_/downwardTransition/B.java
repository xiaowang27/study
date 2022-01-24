package chapter07.polymorphic_.downwardTransition;

public class B extends A{
    private int age;
    public B(String name,int age) {
        super(name);
        this.age= age;
    }
    public void eat(){
        System.out.println("子类的eat()...");
    }
}
