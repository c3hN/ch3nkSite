package com.ch3nk.ch3nkSite.common.response;

/**
 * Ajax响应
 * com.ch3nk.ch3nkSite.common.response
 * 2019-03-04 15:27
 * chenkai
 */
public class AjaxRespBean {
    private static final String SUCCESS     =       "0";
    private static final String FAIL        =       "1";
    private String status;
    private String msg;
    private Object data;


    /*
     * constructor
     */
    public AjaxRespBean() {
    }



    /**
     * 响应失败
     * @return
     */
    public static AjaxRespBean failResponse() {
        return new AjaxRespBean().setStatus(FAIL);
    }

    public static AjaxRespBean failResponse(String msg) {
        return new AjaxRespBean().setStatus(FAIL).setMsg(msg);
    }

    public static AjaxRespBean failResponse(Object date,String msg) {
        return new AjaxRespBean().setStatus(FAIL).setData(date).setMsg(msg);
    }

    /*
     *响应成功
     */
    public static AjaxRespBean successResponse() {
        return new AjaxRespBean().setStatus(SUCCESS);
    }

    public static AjaxRespBean successResponse(String msg) {
        return new AjaxRespBean().setStatus(SUCCESS).setMsg(msg);
    }

    public static AjaxRespBean successResponse(Object data) {
        return new AjaxRespBean().setStatus(SUCCESS).setData(data);
    }

    public static AjaxRespBean successResponse(Object data,String msg) {
        return new AjaxRespBean().setStatus(SUCCESS).setData(data).setMsg(msg);
    }











    /*
     * getter & settrt
     */
    public String getStatus() {
        return status;
    }

    public AjaxRespBean setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public AjaxRespBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public AjaxRespBean setData(Object data) {
        this.data = data;
        return this;
    }
}
