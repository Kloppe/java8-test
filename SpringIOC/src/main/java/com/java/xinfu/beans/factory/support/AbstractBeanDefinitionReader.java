package com.java.xinfu.beans.factory.support;

import com.java.xinfu.beans.factory.config.BeanDefinitionReader;
import com.java.xinfu.beans.factory.config.BeanDefinitionRegistry;
import com.java.xinfu.core.io.Resource;
import com.java.xinfu.core.io.ResourceLoader;
import com.java.xinfu.exception.BeanDefinitionStoreException;
import com.java.xinfu.util.Assert;
import org.dom4j.Document;

/**
 * @author 掘金
 * @date 2018/11/14
 * @desc
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private final BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;


    /**
     * AbstractBNeanDefinitionReader 的构造器
     *
     * @param beanDefinitionRegistry 这里的registry即实现了BeanDefinitionRegistry,有实现了ResourceLaoder
     */
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry){
        Assert.notNull(beanDefinitionRegistry,"BeanDefinitionRegistry must not be null");
        this.registry = beanDefinitionRegistry;
        if(registry instanceof ResourceLoader){
            this.resourceLoader = (ResourceLoader)this.registry;
        }
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    @Override
    public int loadBeanDefinitions(Resource... resources) throws BeanDefinitionStoreException {
        Assert.notNull(resources,"Resource array must not be null");
        int counter = 0;
        for(Resource resource : resources){
            counter += this.loadBeanDefinitions(resource);
        }
        return counter;
    }

    @Override
    public int loadBeanDefinitions(String location) throws BeanDefinitionStoreException {
        return loadBeanDefinitions(this.resourceLoader.getResource(location));
    }

    @Override
    public int loadBeanDefinitions(String... locations) throws BeanDefinitionStoreException {
        Assert.notNull(locations,"Location arrary must not be null");
        int counter = 0;
        for (String location : locations){
            counter += this.loadBeanDefinitions(location);
        }
        return counter;
    }

}
