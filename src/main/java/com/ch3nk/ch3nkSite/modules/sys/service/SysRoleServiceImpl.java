package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysRoleMapper;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public int saveRole(SysRole sysRole) {
        if (StringUtils.isEmpty(sysRole.getRoleId())) {
            SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
            Date date = new Date();
            sysRole.setRoleId(UUIDutil.getUUID());
            sysRole.setCreateBy(user.getUserId());
            sysRole.setUpdateBy(user.getUserId());
            sysRole.setCreateDate(date);
            sysRole.setUpdateDate(date);
            return sysRoleMapper.insertSelective(sysRole);
        }
        return 0;
    }

    @Override
    public void deleteRole(String roleId) {

    }

    @Override
    public int updateRole(SysRole sysRole) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        sysRole.setUpdateDate(new Date());
        sysRole.setUpdateBy(user.getUserId());
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
