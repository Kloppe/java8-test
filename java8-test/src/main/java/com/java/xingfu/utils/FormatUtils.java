package com.java.xingfu.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author 掘金
 * @description
 * @date 2018/11/8
 */
public class FormatUtils {

    /**
     * 金额 相关格式化，保留2位，去除末尾的0
     */
    static DecimalFormat decimalFormat = new DecimalFormat("###################.##");

    /**
     * 去除数据的尾0
     * <p/>
     * 将自动实现四舍五入，保留两位
     *
     * @return
     */
    public static String removeZero(Object number){
        if(number == null){
            return null;
        }
        if(number instanceof String){
            return decimalFormat.format(new BigDecimal((String.valueOf(number))));
        }
        return decimalFormat.format(number);
    }
}
