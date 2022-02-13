package demo02.aopOperation;

import org.springframework.stereotype.Component;

@Component
public class User {
    public void add(){
        int[] nums = {1,2,3};
        // System.out.println(nums[3]);
        System.out.println("add()方法执行了... ...");
    }
}
