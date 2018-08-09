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
     * 查询所有
     * @return
     */
    List<SysMenu> findAll();

    /**
     * 批量逻辑删除
     * @param menuIds
     * @return
     */
    int tombstoneBatch(String [] menuIds);

    /**
     * 批量逻辑还原
     * @param menuIds
     * @return
     */
    int recoveByPKBatch(String [] menuIds);

    /**
     * 保存
     * @param sysMenu
     * @return
     */
    int saveSysMenu(SysMenu sysMenu);



}