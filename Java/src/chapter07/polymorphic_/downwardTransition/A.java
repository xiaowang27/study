package chapter07.polymorphic_.downwardTransition;

public class A {
    private String name;

    public A(String name) {
        this.name = name;
    }

    public void say(){
        System.out.println("父类say()...");
    }
}
