package items.smallChanageSys;

import java.util.Scanner;

public class SmallChanageSys {
    public static void main(String[] args) {
        int status = 1;
        Scanner sc = new Scanner(System.in);
        Money m = new Money(100);
        do {
            System.out.println("----------------零钱通菜单--------------------");
            System.out.printf('\t' + "\t\t\t1.零钱通明细" + '\n' + "\t\t\t\t2.收益入账" + '\n' + "\t\t\t\t3.消费" + '\n' + "\t\t\t\t4.退出" + '\n' + "\t\t\t\t5.余额\n");
            System.out.println("--------------------------------------------");
            System.out.print("请选择:");
            int i = sc.nextInt();
            switch (i) {
                case 1:
                    System.out.println("----------------零钱通明细--------------------");
                    System.out.println(m.logPrint());
                    break;
                case 2:
                    System.out.println("----------------零钱通收益入账--------------------");
                    System.out.print("收益金额:");
                    int money = sc.nextInt();
                    m.add(money);
                    break;
                case 3:
                    System.out.println("----------------零钱通消费--------------------");
                    System.out.print("消费金额:");
                    int shop = sc.nextInt();
                    m.shop(shop);
                    System.out.println();
                    break;
                case 4:
                    System.out.println("----------------零钱通余额--------------------");
                    System.out.println(m.getMoney());
                    break;
                case 5:
                    System.out.println("----------------零钱通退出--------------------");
                    System.out.println("再见~");
                    status = 0;
                    break;
                default:
                    System.out.println("输入编号有误");
                    break;
            }
            System.out.print("返回主菜单输入1，结束操作输入0！请输入：");
            status = sc.nextInt();

        } while (status == 1);
    }
}
