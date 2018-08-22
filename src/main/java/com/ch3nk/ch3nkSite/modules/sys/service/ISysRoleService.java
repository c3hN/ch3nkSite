package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;

import java.util.List;

public interface ISysRoleService {
    int saveRole(SysRole sysRole);

    void deleteRole(String roleId);

    int updateRole(SysRole sysRole);

    List<SysRole> findBy(SysRole sysRole);

    int findCountBy(SysRole sysRole);

}
