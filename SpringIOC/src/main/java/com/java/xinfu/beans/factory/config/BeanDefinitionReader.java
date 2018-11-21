package com.java.xinfu.beans.factory.config;

import com.java.xinfu.core.io.Resource;
import com.java.xinfu.core.io.ResourceLoader;
import com.java.xinfu.exception.BeanDefinitionStoreException;

/**
 * @author 掘金
 * @desc
 * @since 2018/11/14
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException;

    /**
     * 用户解析Resource数组中的Resource对象，将信息转换成BeanDefiniton对象
     *
     * @param resources Resources数组
     * @return 返回所解析的Resource个数
     * @throws BeanDefinitionStoreException
     */
    int loadBeanDefinitions(Resource... resources) throws BeanDefinitionStoreException;

    int loadBeanDefinitions(String location) throws BeanDefinitionStoreException;

    int loadBeanDefinitions(String... locations) throws BeanDefinitionStoreException;
}
