package com.java.xinfu.beans.factory.config;

import com.java.xinfu.exception.BeanDefinitionStoreException;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public interface BeanDefinitionRegistry {

    //BeanDefinition的注册
    void registerBeanDefiniton(String beanName, BeanDefinition beanDefiniton)throws BeanDefinitionStoreException;

    //撤销BeanDefinition的注册
    void removeBeanDefinition(String beanName) throws Exception;

    //根据beanName获得BeanDefinition
    BeanDefinition getBeanDefiniton(String beanName) throws Exception;

    String[] getBeanDefinitonNames();

    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();
}
