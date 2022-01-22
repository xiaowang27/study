package demo01.bean;

public class Orders {
    private String start;

    public Orders() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("第一步：从构造器创建bean实例");
    }

    public void setStart(String start) {
        this.start = start;
        System.out.println("第二步：为bean属性赋值和对其他bean引用");
    }

    public void initMethod(){
        System.out.println("第三步：调用bean的初始化方法");
    }

    public void useBeanMethod(){
        System.out.println("第四步：bean可以被使用");
    }

    public void closeBean(){
        System.out.println("第五步：容器关闭时，调用bean的销毁方法");
    }
}
