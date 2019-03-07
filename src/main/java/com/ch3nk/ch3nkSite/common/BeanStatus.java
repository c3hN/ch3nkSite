package com.ch3nk.ch3nkSite.common;

/**
 * com.ch3nk.ch3nkSite.common
 * 2019-03-04 16:01
 * chenkai
 */
public enum BeanStatus {
    UNDELETED("未删除","0"),DELETED("已删除","1");

    private String msg;
    private String code;

    BeanStatus() {
    }

    BeanStatus(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }}
