package com.java.xingfu.common.enumtype;

public enum ErrorCodeEnum {

    UNKNOWN(-1, "未知异常"),

    ILLEGAL_ARGUMENT(2, "参数异常"),

    DATA_QUERY_FAILURE(3, "数据查询异常"),

    DATA_EXECUTE_FAILURE(4, "数据执行异常"),

    RPC_FAILURE(5, "远程调用异常"),

    ACTIVITY_DISABLE(6, "活动无效"),

    USER_NOT_LOGIN(16,"用户未登录"),

    NO_AUDIT_AUTH(18,"无审核权限"),

    ;

    private final int code;
    private final String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
