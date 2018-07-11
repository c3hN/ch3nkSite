package com.ch3nk.ch3nkSite.modules.sys.entity;

public class SysMenu {
    private static final long serialVersionUID = 1L;

    private String menuId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String eName;

    /**
     * 资源路径
     */
    private String href;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标记 0：deleted
     */
    private Integer deleteFlag;


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

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysMenu other = (SysMenu) that;
        return (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.geteName() == null ? other.geteName() == null : this.geteName().equals(other.geteName()))
                && (this.getHref() == null ? other.getHref() == null : this.getHref().equals(other.getHref()))
                && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((geteName() == null) ? 0 : geteName().hashCode());
        result = prime * result + ((getHref() == null) ? 0 : getHref().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", name=").append(name);
        sb.append(", eName=").append(eName);
        sb.append(", href=").append(href);
        sb.append(", icon=").append(icon);
        sb.append(", remark=").append(remark);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
