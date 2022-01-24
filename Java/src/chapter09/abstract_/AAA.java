package chapter09.abstract_;

public class AAA extends Template{
    @Override
    public void job() {
        System.out.println("数钱");
        int money = 0;
        for (int i = 0; i < 100000000; i++) {
            money+=i;
        }
    }
}
