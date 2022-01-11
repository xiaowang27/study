package chapter04;

public class SwitchTest {
    public static void main(String[] args) {
        String str = "Hello";
        switch (str) {
            case "Hello":
                System.out.println(str);
                break;
            case "world":
                System.out.println("Hello World");
                break;
            default:
                System.out.println("...");
        }
    }
}
