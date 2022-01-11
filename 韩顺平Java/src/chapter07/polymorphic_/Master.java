package chapter07.polymorphic_;

public class Master {
    private String name;

    public Master(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 传统方法 主人给小狗喂食
    public void feed(Dog dog,Bone bone){
        System.out.println("主人"+getName()+"给"+dog.getName()+"吃"+bone.getName());
    }

    // 传统方法 主人给小猫喂食
    public void feedCat(Cat cat,Fish fish){
        System.out.println("主人"+getName()+"给"+cat.getName()+"吃"+fish.getName());
    }

    // 使用多态-对象多态解决
    public void feedExtends(Animal animal,Food food){
        String name = "";
        if(animal.getClass().getSimpleName().equals("Cat")){
            name = "小猫";
        }else if(animal.getClass().getSimpleName().equals("Dog")){
            name = "小狗";
        }
        System.out.println("主人"+getName()+"给"+name+animal.getName()+"吃"+food.getName());
    }
}
