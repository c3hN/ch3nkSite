package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysMenuMapper;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Override
    public int findCount(SysMenu sysMenu) {
        return sysMenuMapper.selectCountSelective(sysMenu);
    }

    @Override
    public List<SysMenu> findBy(SysMenu sysMenu) {
        return sysMenuMapper.selectBy(sysMenu);
    }

    @Override
    public List<SysMenu> findByPage(SysMenu sysMenu,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return sysMenuMapper.selectBy(sysMenu);
    }
    @Override
    public List<SysMenu> findAllParents() {
        List<SysMenu> sysMenus = sysMenuMapper.selectRoots();
        return sysMenus;
    }

    @Override
    public int tombstoneBatch(String[] menuIds) {
        return  sysMenuMapper.tombstoneByPKBatch(menuIds);
    }

    @Override
    public int recoveByPKBatch(String[] menuIds) {
        return sysMenuMapper.recoveByPKBatch(menuIds);
    }

    @Override
    public int saveSysMenu(SysMenu sysMenu) {
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();   //shiro获取当前登录用户信息
        Date date = new Date();
        sysMenu.setMenuId(UUIDutil.getUUID());
        sysMenu.setCreateDate(date);
        sysMenu.setUpdateDate(date);
        sysMenu.setCreateBy(sysUser.getUserId());
        sysMenu.setUpdateBy(sysUser.getUserId());
        return sysMenuMapper.insertSelective(sysMenu);
    }

    @Override
    public int updateMenu(SysMenu sysMenu) {
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        sysMenu.setUpdateBy(sysUser.getUserId());
        sysMenu.setUpdateDate(new Date());
        return sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
    }

}