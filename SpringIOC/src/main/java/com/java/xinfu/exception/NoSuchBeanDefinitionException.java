package com.java.xinfu.exception;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public class NoSuchBeanDefinitionException extends BeansException{

    public NoSuchBeanDefinitionException(){

    }

    public NoSuchBeanDefinitionException(String message){
        super(message);
    }

}
