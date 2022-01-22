package demo01.collection;

public class Students {
    private String stuName;
    private String gender;

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Students{" +
                "stuName='" + stuName + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
