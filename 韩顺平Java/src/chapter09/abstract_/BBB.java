package chapter09.abstract_;

public class BBB extends Template {
    @Override
    public void job() {
        System.out.println("赚钱");
        int money = 0;
        for (long i = 0; i < 1000000000; i++) {
            money += i;
        }
    }
}
