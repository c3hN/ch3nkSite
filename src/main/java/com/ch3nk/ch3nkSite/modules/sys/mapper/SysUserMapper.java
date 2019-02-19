package com.ch3nk.ch3nkSite.modules.sys.mapper;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SysUserMapper {
    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);

    /**
     * 批量新增
     * @param list
     * @return
     */
    int insertBatch(List<SysUser> list);

    /**
     * 选择新增
     * @param sysUser
     * @return
     */
    int insertSelective(SysUser sysUser);

    /**
     * 添加用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    int insertRolesForUser(@Param("userId")String userId,@Param("roleIds")String[] roleIds);

    /**
     * 根据主键删除
     * @param userId
     */
    void deleteByPK(@Param("userId") String userId);

    /**
     * 删除用户角色关联
     * @param userId
     */
    void deleteRolesForUser(@Param("userId")String userId);


    /**
     * 根据主键批量删除
     * @param userIds
     */
    void deleteBatchByPK(@Param("userIds") String[] userIds);

    /**
     * 根据主键更新
     * @param sysUser
     * @return
     */
    int updateByPK(SysUser sysUser);

    /**
     * 根据主键选择更新
     * @param sysUser
     * @return
     */
    int updateByPKSelective(SysUser sysUser);

    /**
     * 根据主键批量修改状态
     * @param userIds
     * @return
     */
    int updateStateByPkBatch(@Param("userIds") String[] userIds,@Param("deleteFlag")String deleteFlag);
    /**
     * 条件查询数量
     * @param sysUser
     * @return
     */
    int selectCountBy(SysUser sysUser);

    /**
     * 条件查询
     * @param sysUser
     * @return
     */
    List<SysUser> selectAllBy(SysUser sysUser);

    List<SysUser> selectUserByPage(@Param("sysUser")SysUser sysUser,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize);

    /**
     * 查询用户拥有的角色
     * @param userId
     * @return
     */
    List<SysRole> selectRolesForUser(@Param("userId") String userId);


    /**
     * 根据主键查找
     * @param userId
     * @return
     */
    SysUser selectByPK(@Param("userId") String userId);

    /**
     * 根据account字段查找
     * @param account
     * @return
     */
    SysUser selectByAccount(@Param("account") String account);

}
