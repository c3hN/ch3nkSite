package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;

import java.util.List;
public interface ISysMenuService {
    /**
     * 选择查询条数
     * @param sysMenu
     * @return
     */
    int findCount(SysMenu sysMenu);
    /**
     * 选择查询,分页
     * @param sysMenu
     * @return
     */
    List<SysMenu> findByPage(SysMenu sysMenu, int pageNum, int pageSize);

    /**
     * 选择查询
     * @param sysMenu
     * @return
     */
    List<SysMenu> findBy(SysMenu sysMenu);

    /**
     * @return
     */
    List<SysMenu> findAllParents();


    /**
     * 保存
     * @param sysMenu
     * @return
     */
    int saveSysMenu(SysMenu sysMenu);

    /**
     * 更新
     * @param sysMenu
     * @return
     */
    int updateMenu(SysMenu sysMenu);


    void deleteByPK(String menuId);



}