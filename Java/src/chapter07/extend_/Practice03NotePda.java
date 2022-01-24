package chapter07.extend_;

public class Practice03NotePda extends Practice03Computer{
    private String color;

    // 构造器
    public Practice03NotePda(String CPU, int memory, int hardDisk, String color) {
        super(CPU, memory, hardDisk);
        this.color = color;
    }

    // get and set
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // 打印方法

    @Override
    public String getDetails() {
        return super.getDetails()+getColor();
    }
}
