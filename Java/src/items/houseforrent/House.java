package items.houseforrent;

import java.util.Scanner;

public class House {
    private String name;
    private String call;
    private String address;
    private double rent;
    private int status;
    private int id;
    Scanner scanner = new Scanner(System.in);
    public House(String name, String call, String address, double rent, int status, int id) {
        this.name = name;
        this.call = call;
        this.address = address;
        this.rent = rent;
        this.status = status;
        this.id = id;
    }

    public House(int id, int status,String address) {
        this.id = id;
        this.status = status;
        this.address = address;
        System.out.print("输入房东姓名：");
        this.setName(scanner.nextLine());

        System.out.print("输入房东电话：");
        this.setCall(scanner.nextLine());

        System.out.print("输入房租：");
        this.setRent(scanner.nextInt());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String houseStatus = "";

        if (this.status == 1) {
            houseStatus = "已租出";
        } else {
            houseStatus = "未租出";
        }


        return "\n\t房东姓名：" + this.name +
                "\n\t房东电话：" + this.call +
                "\n\t房源地址：" + this.address +
                "\n\t房租：" + this.rent +
                "\n\t房屋状态:" + houseStatus +
                "\n\t房屋id:" + this.id;
    }


}
