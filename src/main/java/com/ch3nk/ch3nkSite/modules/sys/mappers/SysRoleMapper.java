package com.ch3nk.ch3nkSite.modules.sys.mappers;

import com.ch3nk.ch3nkSite.common.base.baseMapper.BaseMapper;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 删除角色相关菜单
     * @param roleId
     * @return
     */
    @Delete("delete from sys_role_menu where role_id = #{roleId}")
    int deleteRoleMenus(@Param("roleId") String roleId);

    /**
     * 保存角色相关菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    int insertRoleMenus(@Param("roleId") String roleId,@Param("menuIds") String[] menuIds);

}
