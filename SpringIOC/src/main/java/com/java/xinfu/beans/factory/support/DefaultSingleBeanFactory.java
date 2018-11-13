package com.java.xinfu.beans.factory.support;

import com.java.xinfu.beans.factory.ObjectFactory;
import com.java.xinfu.beans.factory.config.SingletonBeanRegistry;
import com.java.xinfu.exception.BeansException;
import com.java.xinfu.util.Assert;
import com.java.xinfu.util.StringUtils;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public class DefaultSingleBeanFactory implements SingletonBeanRegistry{
    protected static final Object NULL_OBJECT = new Object();
    protected  final Log logger = LogFactory.getLog(this.getClass());
    //单例池，缓存所有已经创建完成的单例bean
    private final Map<String,Object> singletonObjects = new ConcurrentHashMap<>(256);
    //用户缓存所有已经注册的单例bean名称
    private final Set<String> registeredSingletons = new LinkedHashSet<>(256);
    //记录的是依赖项所需要的所有dependentBean
    protected final Map<String, Set<String>> dependentBeanMap = new ConcurrentHashMap<>(64);
    //记录的是dependentBean以及其所有需要的依赖项
    protected final Map<String,Set<String>> dependenciesForBeanMap = new ConcurrentHashMap<>(64);

    public DefaultSingleBeanFactory(){}


    /**
     * 将dependentBeanName和其所依赖的项beanName添加到记录中
     *
     * @param beanName
     * @param dependentBeanName
     */
    public void registerDependentBean(String beanName, String dependentBeanName) {
        //获得依赖项beanName所服务的所有bean
        Set<String> dependentBeans = (Set) this.dependentBeanMap.get(beanName);
        synchronized (this.dependentBeanMap) {
            if (dependentBeans == null || !dependentBeans.contains(dependentBeanName)) {
                if (dependentBeans == null) {
                    dependentBeans = new LinkedHashSet(8);
                    this.dependentBeanMap.put(beanName, dependentBeans);
                }
                ((Set) dependentBeans).add(dependentBeanName);
            }
        }
        synchronized (this.dependenciesForBeanMap) {
            Set<String> dependenciesForBean = (Set) this.dependenciesForBeanMap.get(dependentBeanName);
            if (dependenciesForBean == null) {
                dependenciesForBean = new LinkedHashSet(8);
                this.dependenciesForBeanMap.put(dependentBeanName, dependenciesForBean);
            }
            ((Set) dependenciesForBean).add(beanName);
        }
    }

    public Object getSingleton(String beanName, ObjectFactory<?> singleFactory){
        Assert.notNull(beanName,"'beanName' must not be null");
        synchronized (this.singletonObjects){
            //先从单例池中找单例对象
            Object singletonObject = this.singletonObjects.get(beanName);
            //若找不到已经创建的单例对象，则进行创建
            if(singletonObject == null){
                boolean newSingleton = false;
                try{
                    singletonObject = singleFactory.getObejct();
                    newSingleton = true;
                }catch (IllegalStateException var1) {
                    singletonObject = this.singletonObjects.get(beanName);
                    if(singletonObject == null){
                        throw var1;
                    }
                } catch (BeansException e) {
                    e.fillInStackTrace();
                }
                if (newSingleton){
                    this.addSingleton(beanName,singletonObject);
                }
            }
            return singletonObject != NULL_OBJECT ? singletonObject : null;
        }
    }

    /**
     * 将这个单例的singletonObject添加到registeredSingletons单例注册表中，并将对应beanName从
     * singletonFactoris和earlySinletonObjeacts中移除
     *
     * @param beanName
     * @param singletonObject
     */
    private void addSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletonObjects){
            this.singletonObjects.put(beanName,singletonObject != null?singletonObject:NULL_OBJECT);
            this.registeredSingletons.add(beanName);
        }
    }


    public String[] getDependentBeans(String beanName){
        Set<String> dependentBeans = this.dependentBeanMap.get(beanName);
        return dependentBeans == null ? new String[0] : StringUtils.toStringArray(dependentBeans);
    }

    public String[] getDependenciesForBean(String beanName){
        Set<String> dependenciesForBean = this.dependenciesForBeanMap.get(beanName);
        return dependenciesForBean == null ? new String[0]: dependenciesForBean.toArray(new String[dependenciesForBean.size()]);
    }

    public boolean containsSingleton(String beanName){
        return this.singletonObjects.containsKey(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object beanObject) {

    }

    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = this.singletonObjects.get(beanName);
        return singletonObject != NULL_OBJECT ? singletonObject : null;
    }

    @Override
    public String[] getSingletonNames() {
        return new String[0];
    }

    @Override
    public int getSingletonCount() {
        return 0;
    }
}
