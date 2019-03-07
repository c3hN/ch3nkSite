package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.common.base.baseService.BaseService;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysOperate;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysMenuMapper;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysOperateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("unused")
public class SysMenuService extends BaseService<SysMenu> {
    @Autowired
    private SysMenuMapper menuMapper;
    @Autowired
    private SysOperateMapper sysOperationMapper;

    public List<SysOperate> findMenuOperations(List menuIds){
        return sysOperationMapper.selectByMenuIds(menuIds);
    }

}
