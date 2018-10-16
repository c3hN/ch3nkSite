package com.ch3nk.ch3nkSite.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class SysDic implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 字典主键
     */
    private String dicId;

    /**
     * 父级编号
     */
    private String parentId;

    private String dicName;

    private String dicEname;

    private String dicCode;

    private String dicType;
    /**
     * 描述
     */
    private String remark;
    /**
     * 排序
     */
    private String dicSort;

    public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public String getDicEname() {
        return dicEname;
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }

    public void setDicEname(String dicEname) {
        this.dicEname = dicEname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDicSort() {
        return dicSort;
    }

    public void setDicSort(String dicSort) {
        this.dicSort = dicSort;
    }
}