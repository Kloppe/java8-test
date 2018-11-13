package com.java.xinfu.core.io;

/**
 * @author 掘金
 * @description
 * @date 2018/11/10
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
