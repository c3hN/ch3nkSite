package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;

import java.util.List;

public interface ISysRoleService {
    int saveRole(SysRole sysRole);

    void deleteRole(String roleId);

    int updateRole(SysRole sysRole);

    List<SysRole> findBy(SysRole sysRole);

    SysRole findByRoleId(String roleId);

    List<SysRole> findByPage(SysRole sysRole,int pageNum,int pageSize);

    List<SysRole> findByDeptId(String deptId);

    int findCountBy(SysRole sysRole);

    int saveRoleMenus(String roleId,String[] menuIds);

    void deleteRoleMenus(String roleId);

    List<SysMenu> findMenusByRoleId(String roleId);

}
