package demo01.bean;

public class Emp {
    private String empName;
    private String gender;
    // 表示一个员工只属于一个部门
    private Dept dept;

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    // 要使用外部bean的级联赋值的方式，就必须先定义一个获取外部bean的方法，用于赋值
    public Dept getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empName='" + empName + '\'' +
                ", gender='" + gender + '\'' +
                ", dept=" + dept +
                '}';
    }
}
