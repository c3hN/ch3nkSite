package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysUserMapper;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class SysUserService implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> findUserByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> userList = sysUserMapper.selectAll();
        return userList;
    }

    @Override
    public int findUserCount() {
        return sysUserMapper.selectCount();

    }

    @Override
    public SysUser findUserById(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public SysUser findByAccount(String account) {
        return sysUserMapper.selectByAccount(account);
    }


    @Override
    public int saveUser(SysUser sysUser) {
        String nickName = sysUser.getNickName();
        String account = sysUser.getAccount();
        sysUser.setUserId(UUIDutil.getUUID());
        SimpleHash simpleHash = new SimpleHash("MD5", sysUser.getUserPwd());
        sysUser.setUserPwd(simpleHash.toHex());
        if (StringUtils.isBlank(nickName)){
            sysUser.setNickName("用户"+account);
        }
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        return sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public int updateUser(SysUser sysUser) {
        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public int tombstone(String userId) {
//        sysUserMapper.selectByPrimaryKey(userId).getDeleteFlag()   //0:已删除  1：未删除
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (sysUser.getDeleteFlag() == 0) {
            return 1;
        }
        sysUser.setDeleteFlag(0);
        sysUser.setUpdateTime(new Date());
        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public void importUsersFromExc(File file) {

    }

    @Override
    public void exportUsers2Exc() {

    }


}
