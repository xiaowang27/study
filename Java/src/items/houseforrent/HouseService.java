package items.houseforrent;

import java.util.Scanner;

public class HouseService {
    Scanner sc = new Scanner(System.in);
    HouseController hc = new HouseController();

    public void addHouse() {
        System.out.print("输入房东姓名：");
        String name = sc.nextLine();

        System.out.print("请输入房源id:");
        int id = sc.nextInt();

        System.out.printf("请选择房源状态:未出租请输入1\t已出租请输入0\n请选择：");
        int status = sc.nextInt();

        System.out.print("输入房源地址：");
        String address = sc.next();

        System.out.print("输入房租：");
        double rent = sc.nextDouble();

        System.out.print("输入房东电话：");
        String call = sc.next();

        hc.addHouse(name, call, address, rent, status, id);
    }

    public void queryHouse() {
        System.out.println("请输入房源id:");
        int id = sc.nextInt();
        House house = hc.queryHouse(id);
        System.out.println(house);
    }

    public void removeHouse() {
        System.out.println("请输入房源id:");
        int id = sc.nextInt();
        hc.removeHouse(id);
    }

    public void updateHouse() {
        System.out.println("请输入房源id:");
        int id = sc.nextInt();
        System.out.printf("请选择房源状态:\n未出租请输入1\n已出租请输入0\n请选择：");
        int status = sc.nextInt();
        hc.updateHouse(id, status);
    }

    public void listHouse() {
        hc.houseList();
    }
}
