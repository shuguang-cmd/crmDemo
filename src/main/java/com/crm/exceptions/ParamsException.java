package com.crm.exceptions;

public class ParamsException extends RuntimeException {
    private Integer code = 300;
    private String msg = "操作失败";

    public ParamsException(String msg) {
        super(msg);
        this.msg = msg;
    }
    // Getter/Setter...
}