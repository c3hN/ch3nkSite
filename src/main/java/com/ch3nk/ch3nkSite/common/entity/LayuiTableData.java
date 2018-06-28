package com.ch3nk.ch3nkSite.common.entity;

import java.io.Serializable;
import java.util.List;

/*layui数据表格数据封装类
格式：
        {
            "code":0,
            "msg":"",
            "count":1000,
            "data":[
            {
            "id":10000,
            "username":"user-0",
            "sex":"女",
            "city":"城市-0",
            "sign":"签名-0",
            "experience":255,
            "logins":24,
            "wealth":82830700,
            "classify":"作家",
            "score":57
            }
*/
public class LayuiTableData implements Serializable{
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private int count;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
