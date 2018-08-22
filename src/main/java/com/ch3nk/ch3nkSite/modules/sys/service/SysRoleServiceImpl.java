package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public int saveRole(SysRole sysRole) {
        return 0;
    }

    @Override
    public void deleteRole(String roleId) {

    }

    @Override
    public int updateRole(SysRole sysRole) {
        return sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    @Override
    public List<SysRole> findBy(SysRole sysRole) {
        return sysRoleMapper.selectBy(sysRole);
    }

    @Override
    public int findCountBy(SysRole sysRole) {
        return sysRoleMapper.selectCountBy(sysRole);
    }
}
