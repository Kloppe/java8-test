package com.java.xinfu.exception;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public class BeansException extends Exception{

    public BeansException(){

    }

    public BeansException(String message){
        super(message);
    }

    public BeansException(String msg,Throwable cause){
        super(msg,cause);
    }
}
