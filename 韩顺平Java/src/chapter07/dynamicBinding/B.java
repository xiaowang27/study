package chapter07.dynamicBinding;

public class B extends A{
    public int i = 20;

    public int getI() {
        return i;
    }

    public int sum(){
        return i+20;
    }

    public int sum1(){
        return i+10;
    }
}
