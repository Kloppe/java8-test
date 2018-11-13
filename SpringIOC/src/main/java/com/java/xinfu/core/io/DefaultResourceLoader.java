package com.java.xinfu.core.io;

import com.java.xinfu.util.Assert;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location,"location must not be null");
        //如果以"classpath:"开头，则尝试创建ClassPathResource(暂时不实现)
        if(location.startsWith(ResourceLoader.CLASSPATH_URL_PREFIX))
            return null;
        return new FileSystemResource(location);
    }
}
