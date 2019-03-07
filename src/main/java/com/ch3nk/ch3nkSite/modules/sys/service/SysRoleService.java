package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.common.base.baseService.BaseService;
import com.ch3nk.ch3nkSite.common.exception.AffectedRowIsZeroException;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@SuppressWarnings("unused")
public class SysRoleService extends BaseService<SysRole> {
    @Autowired
    private SysRoleMapper mapper;

    public int count(SysRole sysRole) {
        return mapper.count(sysRole);
    }

    /**
     * 添加角色的菜单
     * @param roleId
     * @param menuIds
     */
    public void addRoleMenus(String roleId,String menuIds) throws AffectedRowIsZeroException{
        deleteRoleMenus(roleId);
        if (StringUtils.isEmpty(menuIds)) {
            return ;
        }
        String[] mIds = menuIds.split(",");
        if (mIds.length > 0) {
            int i = mapper.insertRoleMenus(roleId, mIds);
            if (i == 0) {
                throw new AffectedRowIsZeroException("0 row(s) inserted");
            }
        }
    }


    public void deleteRoleMenus(String roleId) throws AffectedRowIsZeroException{
        mapper.deleteRoleMenus(roleId);
    }
}
