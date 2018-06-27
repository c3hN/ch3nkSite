package com.ch3nk.ch3nkSite.modules.sys.mapper;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;

public interface SysRoleMapper {
    /**
     * 添加角色
     * @param sysRole
     * @return
     */
    int add(SysRole sysRole);

    /**
     * 更新角色信息
     * @param sysRole
     * @return
     */
    int update(SysRole sysRole);

}
