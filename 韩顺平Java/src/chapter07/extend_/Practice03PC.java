package chapter07.extend_;

public class Practice03PC extends Practice03Computer {
    private String brand;

    // 构造器
    public Practice03PC(String CPU, int memory, int hardDisk, String brand) {
        // 这里也体现继承设计的基本思想，父类构造器完成父类属性的初始化；子类构造器完成子类属性的初始化
        super(CPU, memory, hardDisk);
        this.brand = brand;
    }

    // get and set
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    // 打印方法
    @Override
    public String getDetails() {
        return super.getDetails() + getBrand();
    }
}
