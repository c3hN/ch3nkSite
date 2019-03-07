package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.common.base.baseService.BaseService;
import com.ch3nk.ch3nkSite.common.exception.AffectedRowIsZeroException;
import com.ch3nk.ch3nkSite.common.exception.IllegalPasswordException;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccount;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccountRole;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysOperate;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysAccountMapper;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysAccountRoleMapper;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysOperateMapper;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysRoleMenuMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("unused")
public class SysAccountService extends BaseService<SysAccount> {
    @Autowired
    private SysAccountMapper mapper;
    @Autowired
    private SysAccountRoleMapper accountRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysOperateMapper sysOperateMapper;

    public int count(SysAccount account) {
        return mapper.count(account);
    }

    @Override
    public int insert(SysAccount sysAccount) throws IllegalPasswordException {
        String pswd = sysAccount.getPassword();
        if (StringUtils.isNotEmpty(pswd)) {
            String newPswd = new SimpleHash("MD5",pswd).toHex();
            sysAccount.setPassword(newPswd);
            int i = mapper.insertSelective(sysAccount);
            if (i == 0) {
                throw new AffectedRowIsZeroException("0 row(s) inserted");
            }else {
                return i;
            }
        }else {
            throw new IllegalPasswordException("密码为空");
        }
    }

    public void saveAccountRoles(String acId, String roleIds) {
        if (StringUtils.isEmpty(roleIds)) {
            accountRoleMapper.deleteAccountRoles(acId);//删除旧关联
        }else {
            String[] rIds = roleIds.split(",");
            accountRoleMapper.deleteAccountRoles(acId);//先删除旧关联
            int i = accountRoleMapper.insertAccountRoles(acId, rIds);
            if (i == 0) {
                throw new AffectedRowIsZeroException("0 row(s) inserted");
            }
        }

    }

    public List<SysAccountRole> findAccountRoles(String acId) {
        return accountRoleMapper.selectAccountRoles(acId);
    }

    /**
     * 查询账号菜单/操作权限
     * @param acId
     * @return
     */
    public List<String> findAccountCodes(String acId) {
        List<String> rIds = new ArrayList<String>();
        List<String> mIds = new ArrayList<String>();
        List<String> codes = new ArrayList<String>();
        //查询账号的角色
        List<SysAccountRole> sysAccountRoles = accountRoleMapper.selectAccountRoles(acId);
        for (SysAccountRole s: sysAccountRoles) {
            rIds.add(s.getRole().getRoleId());
        }
        List<SysMenu> sysMenus = sysRoleMenuMapper.selectMenusByRoles(rIds);
        if (sysMenus.size() != 0) {
            for (SysMenu s : sysMenus) {
                mIds.add(s.getMenuId());
                codes.add(s.getCode());
            }
            List<SysOperate> sysOperates = sysOperateMapper.selectByMenuIds(mIds);
            for (SysOperate s : sysOperates) {
                codes.add(s.getCode());
            }
        }
        return codes;
    }

}
