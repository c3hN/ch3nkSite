package com.ch3nk.ch3nkSite.common.base.baseController;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccount;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;

@Slf4j
@SuppressWarnings("unused")
public abstract class BaseController {


    /**
     * 获取当前登录用户账号
     * @return
     */
    protected Object getCurrentAccount() {
        return (SysAccount) SecurityUtils.getSubject().getPrincipal();
    }
}
