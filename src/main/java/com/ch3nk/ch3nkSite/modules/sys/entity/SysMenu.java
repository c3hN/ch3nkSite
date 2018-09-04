package com.ch3nk.ch3nkSite.modules.sys.entity;

import com.ch3nk.ch3nkSite.common.processor.JsonDate2StringProcessor;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    private String menuId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String menuCode;

    /**
     * 父菜单id
     */
    private String parentId;

    /**
     * 类别 0:管理菜单 1:操作权限
     */
    private String category;

    /**
     * 资源路径
     */
    private String href;

    /**
     * 图标路径/名称
     */
    private String menuIcon;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    /**
     * 删除标记 0：deleted
     */
    private String deleteFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * shiro权限表达式
     */
    private String permission;

    private String hasBranch;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String icon) {
        this.menuIcon = menuIcon;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @JsonSerialize(using = JsonDate2StringProcessor.class)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    @JsonSerialize(using = JsonDate2StringProcessor.class)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getHasBranch() {
        return hasBranch;
    }

    public void setHasBranch(String hasBranch) {
        this.hasBranch = hasBranch;
    }
}