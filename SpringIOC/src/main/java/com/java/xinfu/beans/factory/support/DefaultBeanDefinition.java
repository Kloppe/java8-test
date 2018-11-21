package com.java.xinfu.beans.factory.support;

import com.java.xinfu.beans.MutablePropertyValues;
import com.java.xinfu.beans.PropertyValues;
import com.java.xinfu.beans.factory.config.BeanDefinition;
import com.java.xinfu.core.io.Resource;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author 掘金
 * @date 2018/11/11
 * @desc
 */
public class DefaultBeanDefinition implements BeanDefinition {
    //bean的作用域
    private String scope = BeanDefinition.SCOPE_SINGLETON;
    //该bean的class对象
    private Object beanClass;
    //所依赖的bean的类名
    private String[] dependOn;
    //该bean的描述
    private String description;
    //对应的Resource对象
    private Resource resoure;
    //属性值
    private MutablePropertyValues propertyValues;
    //工厂名称
    private String factoryBeanName;

    public DefaultBeanDefinition(){super();}

    private DefaultBeanDefinition(DefaultBeanDefinition original){
        this.setScope(original.getScope());
        this.setBeanClass((Class<?>) original.beanClass);
        this.setDependsOn(original.getDependsOn());
        this.setDescription(original.description);
        this.setResoure(original.resoure);
        this.setPropertyValues(new MutablePropertyValues(original.getPropertyValues()));
    }

    public DefaultBeanDefinition cloneBeanDefinition(){
        return new DefaultBeanDefinition(this);
    }

    public Class<?> getBeanClass()throws IllegalStateException{
        Object beanClassObject = this.beanClass;
        if(beanClassObject == null){
            throw  new IllegalStateException("No beans class specified on beans definition: 没有指定bean的CLass对象");
        }else if(!(beanClassObject instanceof Class)){
            throw new IllegalStateException("Bean class name [" + beanClassObject + "] has not bean resolved into" +
                "an actual Class:不是Class对象");
        }
        return (Class)beanClassObject;
    }

    private void setPropertyValues(MutablePropertyValues propertyValues) {
        this.propertyValues = propertyValues != null ? propertyValues : new MutablePropertyValues();
    }

    private void setResoure(Resource resoure) {
        this.resoure = resoure;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setDependsOn(String[] dependOn) {
        this.dependOn = dependOn;
    }


    public void setBeanClass(Class<?> beanClass){
        this.beanClass = beanClass;
    }



    @Override
    public String[] getDependsOn() {
        return dependOn;
    }

    @Override
    public String getBeanClassName() {
        Object beanClassObject = this.beanClass;
        return beanClassObject instanceof Class ?(((Class) beanClassObject).getName()):(String)beanClassObject;
    }

    @Override
    public void setBeanClassName(String beanClassName) {
        this.beanClass = beanClassName;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public boolean isSingleton() {
        return this.scope.endsWith(BeanDefinition.SCOPE_SINGLETON);
    }

    @Override
    public boolean isPrototype() {
        return !this.isSingleton();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setFactorybeanName(String factorybeanName) {
        this.factoryBeanName = factorybeanName;
    }

    @Override
    public String getFactoryBeanName() {
        return this.factoryBeanName;
    }

    @Override
    public PropertyValues getPropertyValues() {
        return this.propertyValues;
    }

    @Override
    public String getResourceDescription() {
        return this.resoure != null ? this.resoure.getDescription() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DefaultBeanDefinition that = (DefaultBeanDefinition) o;
        return Objects.equals(scope, that.scope) &&
            Objects.equals(beanClass, that.beanClass) &&
            Arrays.equals(dependOn, that.dependOn) &&
            Objects.equals(description, that.description) &&
            Objects.equals(resoure, that.resoure) &&
            Objects.equals(propertyValues, that.propertyValues) &&
            Objects.equals(factoryBeanName, that.factoryBeanName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(scope, beanClass, description, resoure, propertyValues, factoryBeanName);
        result = 31 * result + Arrays.hashCode(dependOn);
        return result;
    }
}
