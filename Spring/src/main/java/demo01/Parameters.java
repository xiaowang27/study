package demo01;

// 测试当类有多个参数时，使用构造器如何注入
public class Parameters {
    private int para1;
    private int para2;
    private int para3;

    public Parameters(int para1, int para2, int para3) {
        this.para1 = para1;
        this.para2 = para2;
        this.para3 = para3;
    }
}
