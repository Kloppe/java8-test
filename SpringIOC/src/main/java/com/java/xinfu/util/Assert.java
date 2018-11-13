package com.java.xinfu.util;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public class Assert {
    public static void notNull(Object object,String message){
        if(object == null) throw new IllegalArgumentException(message);
    }

    public static void hasText(String beanName, String message) {
        if(!StringUtils.hasText(beanName)){
            throw new IllegalArgumentException(message);
        }
    }
}
