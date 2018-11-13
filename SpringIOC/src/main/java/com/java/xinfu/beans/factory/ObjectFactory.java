package com.java.xinfu.beans.factory;

import com.java.xinfu.exception.BeansException;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public interface ObjectFactory<T> {
    T getObejct() throws BeansException;
}
