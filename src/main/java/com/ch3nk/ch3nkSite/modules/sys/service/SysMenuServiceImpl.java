//package com.ch3nk.ch3nkSite.modules.sys.service;
//
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
//import com.ch3nk.ch3nkSite.modules.sys.mapper.SysMenuMapper;
//import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
//import com.github.pagehelper.PageHelper;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.SecurityUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//import java.util.List;
//@Service
//@Transactional(readOnly = true)
//public class SysMenuServiceImpl implements ISysMenuService {
//
//    @Autowired
//    private SysMenuMapper sysMenuMapper;
//
//
//    @Override
//    public int findCount(SysMenu sysMenu) {
//        return sysMenuMapper.selectCountSelective(sysMenu);
//    }
//
//    @Override
//    public List<SysMenu> findBy(SysMenu sysMenu) {
//        return sysMenuMapper.selectBy(sysMenu);
//    }
//
//    @Override
//    public List<SysMenu> findByParent(String parentId){
//        SysMenu menu = new SysMenu();
//        menu.setParentId(parentId);
//        return sysMenuMapper.selectBy(menu);
//    }
//
//    @Override
//    public List<SysMenu> findByCategory(String category) {
//        SysMenu sysMenu = new SysMenu();
//        sysMenu.setCategory(category);
//        return sysMenuMapper.selectBy(sysMenu);
//    }
//
//    @Override
//    public SysMenu findByMenuId(String menuId) {
//        return sysMenuMapper.selectByPrimaryKey(menuId);
//    }
//
//    @Override
//    public List<SysMenu> findByPage(SysMenu sysMenu,int pageNum,int pageSize) {
//        PageHelper.startPage(pageNum, pageSize);
//        return sysMenuMapper.selectBy(sysMenu);
//    }
//    @Override
//    public List<SysMenu> findAllParents() {
//        List<SysMenu> sysMenus = sysMenuMapper.selectRoots();
//        return sysMenus;
//    }
//
//
//    @Override
//    public int saveSysMenu(SysMenu sysMenu) {
//        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();   //shiro获取当前登录用户信息
//        Date date = new Date();
//        sysMenu.setMenuId(UUIDutil.getUUID());
//        sysMenu.setCreateDate(date);
//        sysMenu.setUpdateDate(date);
//        sysMenu.setCreateBy(sysUser.getUserId());
//        sysMenu.setUpdateBy(sysUser.getUserId());
//        return sysMenuMapper.insertSelective(sysMenu);
//    }
//
//    @Override
//    public int updateMenu(SysMenu sysMenu) {
//        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
//        sysMenu.setUpdateBy(sysUser.getUserId());
//        sysMenu.setUpdateDate(new Date());
//        return sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
//    }
//
//    @Override
//    public int updateHasBranch(String menuId, String hasBranch) {
//        if (StringUtils.isAnyEmpty(menuId,hasBranch)) {
//            return 0;
//        }
//        SysMenu menu = new SysMenu();
//        menu.setMenuId(menuId);
//        menu.setHasBranch(hasBranch);
//        return sysMenuMapper.updateByPrimaryKeySelective(menu);
//    }
//
//    @Override
//    public void deleteByPK(String menuId) {
//        sysMenuMapper.deleteByPrimaryKey(menuId);
//    }
//
//}