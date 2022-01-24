package chapter04;

public class DetailSeven {
    public static void main(String[] args) {
        // 随机生成1-100的一个数，直到生成97
        int i = 0;
        int sum = 0;
        do {
            i = (int) (Math.random() * 100) + 1;
            sum++;
        } while (i != 97);
        System.out.println("共随机" + sum + "次");
    }
}
