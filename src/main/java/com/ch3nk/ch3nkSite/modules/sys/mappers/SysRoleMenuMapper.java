package com.ch3nk.ch3nkSite.modules.sys.mappers;

import com.ch3nk.ch3nkSite.common.base.baseMapper.BaseMapper;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * com.ch3nk.ch3nkSite.modules.sys.mappers
 * chenkai
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 查询角色菜单
     * @param roleId
     * @return
     */
    List<SysRoleMenu> selectRoleMenus(@Param("roleId") String roleId);

    /**
     * 根据角色id批量查询菜单
     * @param roleIds
     * @return
     */
    List<SysMenu> selectMenusByRoles(@Param("roleIds")List<String> roleIds);
}
