package chapter09.single_;

public class SingleTon01 {
    public static void main(String[] args) {
        GirlFriend girlFriend01 = new GirlFriend("小花");
        GirlFriend girlFriend02 = new GirlFriend("小红");
        // 这样就不是单例的

        BoyFriend instance = BoyFriend.getInstance();
        System.out.println(instance);
        // 这样就是单例的
    }
}

// 有一个类，GirlFriend
class GirlFriend {
    private String name;

    public GirlFriend(String name) {
        this.name = name;
    }
}

// 有一个类，BoyFriend
class BoyFriend {
    private String name;

    private BoyFriend(String name) {
        this.name = name;
    }

    private static BoyFriend boyFriend = new BoyFriend("boy");

    public static BoyFriend getInstance() {
        return boyFriend;
    }

    @Override
    public String toString() {
        return "BoyFriend{" +
                "name='" + name + '\'' +
                '}';
    }
}
