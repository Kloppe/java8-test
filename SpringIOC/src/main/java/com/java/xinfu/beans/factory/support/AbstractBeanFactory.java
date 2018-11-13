package com.java.xinfu.beans.factory.support;

import com.java.xinfu.beans.factory.BeanFactory;
import com.java.xinfu.exception.BeanCreationException;
import com.java.xinfu.exception.BeansException;
import com.java.xinfu.exception.NoSuchBeanDefinitionException;
import com.java.xinfu.util.Assert;
import com.java.xinfu.util.StringUtils;
import com.sun.tools.classfile.Dependencies;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public abstract class AbstractBeanFactory extends DefaultSingleBeanFactory implements BeanFactory {
    //缓存所有的DefaultBeanDefinition
    private final Map<String, DefaultBeanDefinition> mergedBeanDefinitions = new ConcurrentHashMap<>(256);

    public AbstractBeanFactory(){}


    @Override
    public Object getBean(String name) throws BeansException {
        return this.doGetBean(name,(Class)null,(Object[])null);
    }

    /**
     *  bean详细获取步骤
     */
    private <T> T doGetBean(String name, Class<T> requiredType, Object[] args) throws BeansException {
        //删除name中的&字符，得到真正的beanName
        final String beanName = this.transformedBeanName(name);
        //从缓存中取是否被创建过得单例类型bean,如果有直接去并返回
        Object shareInstance = this.getSingleton(name);
        Object bean;
        if(shareInstance != null && args == null){
            bean = shareInstance;
        }else {
            //查找对应的DefaultBeanDefinition
            final DefaultBeanDefinition mbd = this.getMergedLocalBeanDefinition(beanName);
            //获取依赖项
            String[] dependsOn = mbd.getDependOn();
            String[] var11;
            //若存在依赖项，调用地柜直到找到没有依赖项的bean进行创建（重点）
            if(dependsOn != null){
                var11 = dependsOn;
                //分别处理每一个依赖：若存在循环依赖则报错，否则记录依赖关系
                for (int i = 0; i < dependsOn.length; i++) {
                    String dep = StringUtils.getDependentBeanName(var11[i]);
                    //检查dep是否也（直接或间接）含有依赖项bean,形成循环依赖，若有则报错
                    if(this.isDependent(beanName,dep)){
                        throw new BeanCreationException(mbd.getDescription(), beanName, "Circular depends-on relationship between '" + beanName + "' and '" + dep + "'");
                    }
                    //检查是否已经注册过依赖关系
                    if(!this.isDependencyForBean(dep,beanName)){
                        //记录这一对依赖关系
                        this.registerDependentBean(dep, beanName);
                        //递归，获得这个依赖项
                        this.getBean(dep);
                    }
                }
            }
        }
    }

    private boolean isDependencyForBean(String depend, String beanName) {
        Set<String> dependenciesForBean = this.dependenciesForBeanMap.get(beanName);
        return dependenciesForBean != null && dependenciesForBean.contains(depend);
    }

    private boolean isDependent(String beanName, String dependentBeanName) {
        return this.isDependent(beanName,dependentBeanName,null);
    }

    private boolean isDependent(String beanName, String dependentBeanName, Set<String> alreadySeen) {
        if(alreadySeen != null && (alreadySeen).contains(beanName)){
            return false;
        }else{
            //获取依赖项beanName所需要的所有bean
            Set<String> dependentBeans = this.dependentBeanMap.get(beanName);
            if(dependentBeans == null){
                //若没有依赖则返回false
                return false;
            }else if(dependentBeans.contains(dependentBeanName)){
                //若直接包含则返回true
                return true;
            }else{
                //查看是否存在间接依赖
                Iterator iterator = dependentBeans.iterator();
                String transitiveDependency;
                do{
                    if(!iterator.hasNext()){
                        return false;
                    }
                    transitiveDependency = (String)iterator.next();
                    if(alreadySeen == null){
                        alreadySeen = new HashSet<>();
                    }
                    alreadySeen.add(beanName);
                }while (!this.isDependent(transitiveDependency,dependentBeanName,alreadySeen));
            }
        }
    }



    public void setMergedBeanDefinition(String beanName, DefaultBeanDefinition beanDefinition) {
        mergedBeanDefinitions.put(beanName, beanDefinition);
    }

    private DefaultBeanDefinition getMergedLocalBeanDefinition(String beanName) {
        return this.mergedBeanDefinitions.get(beanName);
    }

    private String transformedBeanName(String name) {
        Assert.notNull(name,"StringNotNull");
        return name.startsWith("&")?name.substring(1):name;
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) throws BeansException {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return null;
    }
}
