package items.houseforrent;

import java.util.Scanner;

public class HouseView {

    public void run(){
        int status = 1;
        do{
            status = new HouseView().view();
        }while (status==1);

    }

    public int view() {
        System.out.println("----------房屋出租系统----------");
        System.out.println("1. 新增房源");
        System.out.println("2. 查找房源");
        System.out.println("3. 删除房源");
        System.out.println("4. 修改房源");
        System.out.println("5. 房源列表");
        System.out.println("6. 退出系统");
        System.out.println("------------------------------");
        System.out.print("请选择:");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        HouseService houseService = new HouseService();
        switch (i) {
            case 1:
                houseService.addHouse();
                return 1;
            case 2:
                houseService.queryHouse();
                return 1;
            case 3:
                houseService.removeHouse();
                return 1;
            case 4:
                houseService.updateHouse();
                return 1;
            case 5:
                houseService.listHouse();
                return 1;
            case 6:
                break;
            default:
                System.out.println("错误选择");
                return 1;
        }
        return 0;
    }
}
