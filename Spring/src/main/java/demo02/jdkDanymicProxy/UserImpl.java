package demo02.jdkDanymicProxy;

public class UserImpl implements User {
    @Override
    public int add(int a,int b) {
        return a+b;
    }

    @Override
    public StringBuilder update(StringBuilder id) {
        return id.append("...");
    }
}
