package com.java.xinfu.beans.factory;

import com.java.xinfu.exception.BeansException;
import com.java.xinfu.exception.NoSuchBeanDefinitionException;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public interface BeanFactory {
    Object getBean(String name) throws BeansException;

    <T> T getBean(String name,Class<T> clazz) throws BeansException;

    boolean containsBean(String name);

    boolean isSingleton(String name) throws NoSuchBeanDefinitionException;

    boolean isPrototype(String name) throws NoSuchBeanDefinitionException;

    Class<?> getType(String name) throws NoSuchBeanDefinitionException;

}
