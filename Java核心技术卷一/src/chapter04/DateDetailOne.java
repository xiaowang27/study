package chapter04;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class DateDetailOne {
    public static void main(String[] args) {
        // Date
        Date date = new Date();
        System.out.println(date);
        String s = date.toString();
        System.out.println(s);

        // LocalDate
        LocalDate localDate = LocalDate.now();  // 工程方法创建的对象
        System.out.println(localDate);  // 2021-12-26
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        System.out.println(year + "年" + month + "月" + day + "日"); //2021年12月26日
    }
}
