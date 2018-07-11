package com.ch3nk.ch3nkSite.modules.sys.mapper;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}
