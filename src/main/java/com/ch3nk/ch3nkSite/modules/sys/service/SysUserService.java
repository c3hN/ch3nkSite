package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysUserMapper;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import com.github.pagehelper.PageHelper;
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
        return 0;
    }

    @Override
    public int updateUser(SysUser sysUser) {
        return 0;
    }

    @Override
    public List<SysUser> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> userList = sysUserMapper.findAll();
        return userList;
    }

    @Override
    public int findCount() {
        return sysUserMapper.findCount();
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
