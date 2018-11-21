package com.java.xingfu.common.utils;

import com.java.xingfu.common.enumtype.ErrorCodeEnum;
import com.java.xingfu.common.exception.BizException;

/**
 * @author 掘金
 * @date 2018/11/21
 * @desc
 */
public class BizExceptionUtils {
    public static BizException build(int code,String msg){
        return new BizException(code,msg);
    }

    public static BizException build(String msg) {
        return new BizException(ErrorCodeEnum.UNKNOWN.getCode(), msg);
    }

    public static BizException build(int code) {
        return new BizException(code, ErrorCodeEnum.UNKNOWN.getMsg());
    }

    public static BizException build(ErrorCodeEnum errorCode) {
        return new BizException(errorCode.getCode(), errorCode.getMsg());
    }
}
