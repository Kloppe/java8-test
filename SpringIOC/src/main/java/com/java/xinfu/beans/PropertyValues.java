package com.java.xinfu.beans;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public interface PropertyValues {
    PropertyValue[] getPropertyValues();

    PropertyValue getPropertyValue(String var1);

    PropertyValues changesSinces(PropertyValues va1);

    boolean contains(String var1);

    boolean isEmpty();
}
