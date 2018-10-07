package com.ch3nk.ch3nkSite.modules.sys.entity;

import com.ch3nk.ch3nkSite.common.processor.JsonDate2StringProcessor;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户实基本信息
 */
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private String userId;

    /**
     * 登录账户
     */
    private String account;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String userPwd;

    /**
     * 头像地址
     */
    private String userPhoto;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 允许登录标记 0:no 1:yes
     */
    private String loginFlag;

    /**
     * 删除标记 0:删除  1：未删除
     */
    private String deleteFlag;

    /**
     * 角色列表
     */
    private List<SysRole> roles;
    /**
     * 用户所属部门
     */
    private SysDepartment department;

    /**
     * 模糊查询字段
     * @return
     */
    private String likeAccount;
    private String likeNickName;
    private String likeCreateTime;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
    @JsonSerialize(using = JsonDate2StringProcessor.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonSerialize(using = JsonDate2StringProcessor.class)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public String getLikeAccount() {
        return likeAccount;
    }

    public void setLikeAccount(String likeAccount) {
        this.likeAccount = likeAccount;
    }

    public String getLikeNickName() {
        return likeNickName;
    }

    public void setLikeNickName(String likeNickName) {
        this.likeNickName = likeNickName;
    }

    public String getLikeCreateTime() {
        return likeCreateTime;
    }

    public void setLikeCreateTime(String likeCreateTime) {
        this.likeCreateTime = likeCreateTime;
    }

    public SysDepartment getDepartment() {
        return department;
    }

    public void setDepartment(SysDepartment department) {
        this.department = department;
    }
}
