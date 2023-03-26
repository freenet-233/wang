package com.wang.common.exception;

/**
 * @author wang
 */
public enum ErrorCode {
    ERROR_201("201"),
    ERROR_400("400"),
    ERROR_401("401"),
    ERROR_500("500"),
    ERROR_510("510"),
    SUCCESS("200");
    private String code;


    ErrorCode(String code) {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
