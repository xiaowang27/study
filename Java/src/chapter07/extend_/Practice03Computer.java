package chapter07.extend_;

public class Practice03Computer {
    private String CPU;
    private int memory;
    private int HardDisk;

    // 构造器
    public Practice03Computer(String CPU, int memory, int hardDisk) {
        this.CPU = CPU;
        this.memory = memory;
        HardDisk = hardDisk;
    }

    // get and set
    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getHardDisk() {
        return HardDisk;
    }

    public void setHardDisk(int hardDisk) {
        HardDisk = hardDisk;
    }

    // 显示方法
    public String getDetails() {
        return "此计算机CPU品牌为" + CPU + "，内存为" + memory + ",硬盘大小为" + HardDisk;
    }
}
