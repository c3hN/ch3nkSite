package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;

import java.util.List;

/**
 * 用户服务接口
 */
public interface  ISysUserService {
    /**
     * 根据用户id查询用户
     * @param userId
     * @return SysUser对象
     */
    SysUser findById(String userId);

    int saveUser(SysUser sysUser);
    int updateUser(SysUser sysUser);


    /**
     * 根据登录账号查询
     * @param userCount
     * @return SysUser对象
     */
    SysUser findByCount(String userCount);

    /**
     * 查询所有未删除的用户
     * @return list
     */
    List<SysUser> findAll();

    /**
     * 禁止/允许用户登录
     * @param userId
     */
    void updateLoginFlag(String userId);

    /**
     * 删除/还原用户
     * @param userId
     */
    void updateDeleteFlag(String userId);
}
