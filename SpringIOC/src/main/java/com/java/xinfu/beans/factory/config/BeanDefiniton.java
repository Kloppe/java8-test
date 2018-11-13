package com.java.xinfu.beans.factory.config;

import com.java.xinfu.beans.PropertyValues;

/**
 * @author 掘金
 * @description
 * 将配置文件中的bean配置信息转换成Spring的对象属性信息，BeanDefinition接口指定了这个规范
 * <p>
 *  需要考虑bean创建顺利的问题：被依赖的bean需要先创建.
 *  spring实现：在beanDefinition中有个String[] getDependsOn()的方法，可以返回依赖bean的name
 */
public interface BeanDefiniton {
    //单例
    String SCOPE_SINGLETON = "singleton";
    //多例
    String SCOPE_PROTOTYPE = "protorype";

    //获得所有以来的bean的类名数组
    String[] getDependOn();

    //获得该bean的className
    String getBeanClassName();

    void setBeanClassName(String beanClassName);

    void setScope(String scope);

    String getScope();

    boolean isSingleton();

    boolean isPrototype();

    String getDescription();

    void setFactorybeanName(String factorybeanName);

    String getFactoryBeanName();

    PropertyValues getPropertyValues();

    String getResourceDescription();

}
