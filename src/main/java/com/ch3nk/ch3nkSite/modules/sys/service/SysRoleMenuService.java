package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.common.base.baseService.BaseService;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysRoleMenu;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysRoleMapper;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * com.ch3nk.ch3nkSite.modules.sys.service
 * chenkai
 */
@Service
@Transactional
public class SysRoleMenuService extends BaseService<SysRoleMenu> {

    @Autowired
    private SysRoleMenuMapper selectRoleMenus;

    /**
     * 查询角色菜单
     * @param roleId
     * @return
     */
    public List<SysRoleMenu> findRoleMenus(String roleId) {
        return selectRoleMenus.selectRoleMenus(roleId);
    }
}
