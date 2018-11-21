package com.java.xingfu.common.exception;

/**
 * @author 掘金
 * @date 2018/11/21
 * @desc
 */
public class BizException extends RuntimeException{

    private int code;

    public BizException(String message) {
        super(message);
    }

    public BizException(String messageTemplate, Object... params) {
        this(String.format(messageTemplate, params));
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(int code, String message) {
        this(message);
        this.code = code;
    }

    public BizException(int code, String messageTemplate, Object... params) {
        this(code, String.format(messageTemplate, params));
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
