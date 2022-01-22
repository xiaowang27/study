package demo01.bean;

public class Dept {
    private String DeptName;

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "DeptName='" + DeptName + '\'' +
                '}';
    }
}
