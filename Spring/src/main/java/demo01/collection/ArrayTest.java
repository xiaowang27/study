package demo01.collection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayTest {
    private int[] arr;
    private List<String> list;
    private Set<String> set;
    private Map<String,String> map;
    private List<Students> students;

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "ArrayTest{" +
                "arr=" + Arrays.toString(arr) +
                ", \nlist=" + list +
                ", \nset=" + set +
                ", \nmap=" + map +
                ", \nstudents=" + students +
                '}';
    }
}
