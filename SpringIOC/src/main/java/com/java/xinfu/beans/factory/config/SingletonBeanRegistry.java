package com.java.xinfu.beans.factory.config;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public interface SingletonBeanRegistry {
    //将单利bean对象存入单例池（专门存放单例bean的一个容器）
    void registerSingleton(String beanName, Object beanObject);
    //根据name得到到单例bean
    Object getSingleton(String beanName);
    //获取所有单例bean的name
    String[] getSingletonNames();
    //获取当前单例bean的数目
    int getSingletonCount();
}
