package com.java.xinfu.beans.factory.support;

import com.java.xinfu.beans.factory.config.BeanDefinitionRegister;
import com.java.xinfu.beans.factory.config.BeanDefiniton;
import com.java.xinfu.exception.BeanDefinitionStoreException;
import com.java.xinfu.util.Assert;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public class DefaultListableBeanFactory extends AbstractBeanFactory implements BeanDefinitionRegister, Serializable {
    //用户缓存所有的beanDefinition
    private final Map<String, BeanDefiniton> beanDefinitonMap = new ConcurrentHashMap<>(256);
    //用户缓存所有的beanDefinition对象的name
    private volatile List<String> beanDefinitionNames = new ArrayList<>(256);

    public DefaultListableBeanFactory(){}

    @Override
    public boolean containsBeanDefinition(String beanName){
        Assert.notNull(beanName,"Bean name must not be null");
        return  this.beanDefinitonMap.containsKey(beanName);
    }

    public void registerBeanDefinition(String beanName, BeanDefiniton beanDefiniton)throws BeanDefinitionStoreException{
        Assert.hasText(beanName,"Bean name must not be null");
        Assert.notNull(beanDefiniton,"BeanDefinition must not be null");
        BeanDefiniton oldBeanDefinition = this.beanDefinitonMap.get(beanName);
        if(oldBeanDefinition != null){
            //验证 beanDefinition 与 oldBeanDefinition是否相等
            if(!beanDefiniton.equals(oldBeanDefinition)){
                if(this.logger.isInfoEnabled()){
                    this.logger.info("Overriding bean definition for bean '" + beanName + "' with a different definition: "
                        + "replacing [" + oldBeanDefinition + "] with [" +beanDefiniton + "]");
                } else if (this.logger.isDebugEnabled()){
                    this.logger.debug("Overriding bean definition for bean '" + beanName + "' with an equivalent definiton: repalcing [" + oldBeanDefinition + "] with [" + beanDefiniton + "]");
                }
                //默认允许覆盖原先已经存在的BeanDefinition
                this.beanDefinitonMap.put(beanName,beanDefiniton);
            } else {
                //若原先为空
                this.beanDefinitonMap.put(beanName, beanDefiniton);
                this.beanDefinitionNames.add(beanName);
            }
        }
        beanDefiniton = this.beanDefinitonMap.get(beanName);
        //若是DefaultBeanDefinition则同时存到AbstractBeanFactory的mergedBeanDefinition中去
        if(beanDefiniton instanceof DefaultBeanDefinition){
            this.setMergedBeanDefinition(beanName,(DefaultBeanDefinition)beanDefiniton);
        }
    }


}
