package items.houseforrent;

import java.security.cert.Extension;
import java.util.ArrayList;

public class HouseController {
    // 定义成员变量,房源列表
    static ArrayList<House> list = new ArrayList<>();

    // 新增房源
    public void addHouse(String name, String call, String address, double rent, int status, int id) {
        House house = new House(name, call, address, rent, status, id);
        list.add(house);
        System.out.println("添加成功");
    }

    // 查找房源
    public House queryHouse(int id) {
        if (list.size() <= 0) {
            System.out.println("暂无房源");
        } else {
            for (House house : list) {
                if (house.getId() == id) {
                    return house;
                }
            }
        }
        return null;
    }

    // 删除房源
    public int removeHouse(int id) {
        for (House house : list) {
            if (house.getId() == id) {
                list.remove(house);
                return 1;
            }
        }
        return 0;
    }

    // 修改房源
    public int updateHouse(int id, int status) {
        for (House house : list) {
            if (house.getId() == id) {
                String address = house.getAddress();
                house = null;

                House h = new House(id, status, address);
                list.add(h);
                return 1;
            }
        }
        return 0;
    }

    // 房源列表
    public void houseList() {
        for (House h : this.list) {
            System.out.println(h);
            System.out.println("--------------id+"+h.getId()+"--------------");
        }
    }
}
