package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.common.base.baseService.BaseService;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysOperate;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysOperateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * com.ch3nk.ch3nkSite.modules.sys.service
 * chenkai
 */
@Service
@Transactional
public class OperateService extends BaseService<SysOperate> {
    @Autowired
    private SysOperateMapper sysOperateMapper;
}
