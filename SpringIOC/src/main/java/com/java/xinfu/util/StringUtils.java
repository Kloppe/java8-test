package com.java.xinfu.util;

import java.util.Collection;

/**
 * @author 掘金
 * @description
 * @date 2018/11/11
 */
public class StringUtils {
    public static String[] toStringArray(Collection<String> collection){
        return collection == null?null :collection.toArray(new String[collection.size()]);
    }

    public static boolean hasText(String str){
        return hasLength(str) && containsText(str);
    }

    private static boolean containsText(String str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if(!Character.isWhitespace(str.charAt(i))){
                return true;
            }
        }
        return false;
    }

    private static boolean hasLength(String str) {
        return str !=null && !str.isEmpty();
    }

    public static String getDependentBeanName(String dependOn) {
        return dependOn.substring(dependOn.indexOf(";") + 1);
    }
}
