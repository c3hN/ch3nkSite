package com.ch3nk.ch3nkSite.modules.sys.mapper;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(@Param("roleId") String roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(@Param("roleId") String roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    int updateStateBatch(@Param("roleIds")String[] roleIds,@Param("deleteFag") String deleteFlag,@Param("useFlag") String useFlag);

    List<SysRole> selectBy(SysRole sysRole);

    List<SysRole> selectByPage(@Param("sysRole") SysRole sysRole,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);

    List<SysMenu> selectMenusForRole(@Param("roleId")String roleId);

    List<SysMenu> selectMenusForRoles(@Param("roleIds")String[] roleIds);

    int selectCountBy(SysRole sysRole);

    int insertRoleMenus(@Param("roleId")String roleId,@Param("menuIds") String[] menuIds);

    void deleteRoleMenus(@Param("roleId")String roleId);

}
