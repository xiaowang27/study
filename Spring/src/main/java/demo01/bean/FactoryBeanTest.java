package demo01.bean;

import org.springframework.beans.factory.FactoryBean;

public class FactoryBeanTest implements FactoryBean {
    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public Emp getObject() throws Exception {
        return new Emp();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
