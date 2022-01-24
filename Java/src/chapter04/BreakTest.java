package chapter04;

public class BreakTest {
    public static void main(String[] args) {
        label1:
        for (int i = 0; i < 10; i++) {
            if (i == 3) {
                break label1;
            }
        }
    }
}
