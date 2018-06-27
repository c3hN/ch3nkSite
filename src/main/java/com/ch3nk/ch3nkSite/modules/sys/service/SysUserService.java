package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysUserMapper;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class SysUserService implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser findById(String userId) {
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public int saveUser(SysUser sysUser) {
        sysUser.setUserId(UUIDutil.getUUID());
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        return sysUserMapper.addUser(sysUser);
    }

    @Override
    public int updateUser(SysUser sysUser) {
        return 0;
    }


    @Override
    public SysUser findByCount(String userCount) {
        return null;
    }

    @Override
    public List<SysUser> findAll() {
        return null;
    }

    @Override
    public void updateLoginFlag(String userId) {

    }

    @Override
    public void updateDeleteFlag(String userId) {

    }
}
