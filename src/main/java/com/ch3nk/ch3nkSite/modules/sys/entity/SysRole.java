package com.ch3nk.ch3nkSite.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class SysRole implements Serializable{
    /**
     * 主键id
     */
    private String roleId;

    /**
     * 归属机构
     */
    private String officerId;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色英文名称
     */
    private String eName;

    /**
     * 是否可用 0：fales 1:true
     */
    private Integer useAbleFlag;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新者
     */
    private Date updateBy;

    /**
     * 更新者
     */
    private Date updateDate;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 删除标记 0：deleted
     */
    private Integer deleteFlag;

    private static final long serialVersionUID = 1L;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getOfficerId() {
        return officerId;
    }

    public void setOfficerId(String officerId) {
        this.officerId = officerId;
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

    public Integer getUseAbleFlag() {
        return useAbleFlag;
    }

    public void setUseAbleFlag(Integer useAbleFlag) {
        this.useAbleFlag = useAbleFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Date updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
        SysRole other = (SysRole) that;
        return (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
                && (this.getOfficerId() == null ? other.getOfficerId() == null : this.getOfficerId().equals(other.getOfficerId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.geteName() == null ? other.geteName() == null : this.geteName().equals(other.geteName()))
                && (this.getUseAbleFlag() == null ? other.getUseAbleFlag() == null : this.getUseAbleFlag().equals(other.getUseAbleFlag()))
                && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
                && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
                && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
                && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getOfficerId() == null) ? 0 : getOfficerId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((geteName() == null) ? 0 : geteName().hashCode());
        result = prime * result + ((getUseAbleFlag() == null) ? 0 : getUseAbleFlag().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
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
        sb.append(", roleId=").append(roleId);
        sb.append(", officerId=").append(officerId);
        sb.append(", name=").append(name);
        sb.append(", eName=").append(eName);
        sb.append(", useAbleFlag=").append(useAbleFlag);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remark=").append(remark);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
