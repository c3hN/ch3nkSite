package com.ch3nk.ch3nkSite.modules.sys.mapper;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

}
