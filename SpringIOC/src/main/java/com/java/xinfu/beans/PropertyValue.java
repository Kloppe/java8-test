package com.java.xinfu.beans;

import com.java.xinfu.util.Assert;
import com.java.xinfu.util.ObjectUtils;
import java.io.Serializable;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public class PropertyValue implements Serializable {
    private final String name;
    private final Object value;

    public PropertyValue(String name,Object value){
        this.name = name;
        this.value = value;
    }

    public PropertyValue(PropertyValue original){
        Assert.notNull(original,"Original must not be null");
        this.name = original.name;
        this.value = original.value;
    }

    public PropertyValue(PropertyValue original,String newValue){
        Assert.notNull(original,"Original must not be null");
        this.name = original.name;
        this.value = newValue;
    }

    public String getName(){
        return this.name;
    }

    public Object getValue(){
        return this.value;
    }

    public int hashCode(){
        return this.name.hashCode() * 29 + ObjectUtils.nullSafeHashCode(this.value);
    }

    @Override
    public String toString(){
        return "bean property'" + this.name + "'" +"value: " +this.value;
    }
}
