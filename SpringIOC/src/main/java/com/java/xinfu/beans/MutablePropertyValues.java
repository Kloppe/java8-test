package com.java.xinfu.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public class MutablePropertyValues implements PropertyValues, Serializable {

    private final List<PropertyValue> propertyValueList;

    public MutablePropertyValues(){
        this.propertyValueList = new ArrayList<>(0);
    }

    public MutablePropertyValues(PropertyValues original){
        if(original != null){
            PropertyValue[] pvs = original.getPropertyValues();
            this.propertyValueList = new ArrayList<>(pvs.length);
            PropertyValue[] var3 = pvs;
            int var4 = pvs.length;

            for (int i = 0; i < var4; i++) {
                PropertyValue pv = var3[i];
                this.propertyValueList.add(new PropertyValue(pv));
            }
        }else{
            this.propertyValueList = new ArrayList<>(0);
        }
    }

    @Override
    public PropertyValue[] getPropertyValues() {
        return new PropertyValue[0];
    }

    @Override
    public PropertyValue getPropertyValue(String var1) {
        return null;
    }

    @Override
    public PropertyValues changesSinces(PropertyValues va1) {
        return null;
    }

    @Override
    public boolean contains(String var1) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
