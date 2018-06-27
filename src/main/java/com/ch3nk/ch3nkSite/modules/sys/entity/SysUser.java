package com.ch3nk.ch3nkSite.modules.sys.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户实基本信息
 */
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /* 主键id */
    private String userId;
    /* 用户昵称 */
    private String nickName;
    /* 登录账户 */
    private String userCount;
    /* 登录密码 */
    private String userPwd;
    /* 用户头像 */
    private String userPhoto;
    /* 创建时间 */
    private Date createTime;
    /* 更新时间 */
    private Date updateTime;
    /* 是否允许登录 0:不允许 1：允许*/
    private int loginFlag;
    /* 删除标记 0:删除 1：未删除 */
    private int deleteFlag;
    /* 用户角色列表,在向sys_user_role插入用户角色数据时作为容器 */
    private List<SysRole> sysRoles;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(int loginFlag) {
        this.loginFlag = loginFlag;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }
}
