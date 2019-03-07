package com.ch3nk.ch3nkSite.common.base.entity;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccount;
import org.apache.shiro.SecurityUtils;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class BaseEntity implements Serializable {
    public static final long serialVersionUID = 1L;


    public BaseEntity() {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }



    protected SysAccount getCurrentAccount() {
        return (SysAccount) SecurityUtils.getSubject().getPrincipal();
    }

}
