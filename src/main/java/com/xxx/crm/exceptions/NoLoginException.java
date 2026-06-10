package com.xxx.crm.exceptions;

public class NoLoginException extends RuntimeException {
    private Integer code = 401;
    private String msg = "未登录";

    public NoLoginException() {
        super("用户未登录");
    }

    public NoLoginException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
