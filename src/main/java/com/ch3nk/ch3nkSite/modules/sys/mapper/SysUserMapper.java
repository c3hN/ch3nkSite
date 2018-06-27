package com.ch3nk.ch3nkSite.modules.sys.mapper;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    /**
     * 根据用户id查询基本信息 √
     * @param userId
     * @return SysUser对象
     */
    SysUser findById(@Param("userId") String userId);

    /**
     * 根据登录账户查询 √
     * @param userCount
     * @return SysUser对象
     */
    SysUser findByCount(@Param("userCount") String userCount);

    /**
     * 查询所有
     * @return
     */
    List<SysUser> findAll();

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    int addUser(SysUser sysUser);

    /**
     * 更新用户 √
     * @param sysUser
     * @return
     */
    int updateUser(SysUser sysUser);

    /**
     * 逻辑删除用户 √
     * @param userId
     * @return
     */
    int deleteUser(@Param("userId") String userId);

    /**
     * 添加用户角色关联
     * @param sysUser
     * @return
     */
    int addUserRole(SysUser sysUser);

    /**
     * 删除用户角色关联
     * @param user_role_id
     * @return
     */
    int deleteUserRole(@Param("user_role_id") String user_role_id);

    /**
     * 查询所有用户数量
     * @param
     * @return
     */
    int findCount();


}
