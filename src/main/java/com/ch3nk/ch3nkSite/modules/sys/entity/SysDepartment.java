package com.ch3nk.ch3nkSite.modules.sys.entity;

import java.util.Date;

public class SysDepartment {
    private static final long serialVersionUID = 1L;
    private String deptId;

    private String deptName;

    private String deptNum;

    /**
     * 简称
     */
    private String deptAbbr;

    private String parentId;

    private Date createDate;

    private Date updateDate;

    private String state;
    private String hasBranch;

    public String getHasBranch() {
        return hasBranch;
    }

    public void setHasBranch(String hasBranch) {
        this.hasBranch = hasBranch;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptNum() {
        return deptNum;
    }

    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    public String getDeptAbbr() {
        return deptAbbr;
    }

    public void setDeptAbbr(String deptAbbr) {
        this.deptAbbr = deptAbbr;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
