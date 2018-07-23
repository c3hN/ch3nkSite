package com.ch3nk.ch3nkSite.common.shiro.realm;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysUserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SystemRealm extends AuthorizingRealm {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public String getName() {
        return "SystemRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
//        String userId = sysUser.getUserId();
//        List<SysRole> roles = sysUserMapper.findById(userId).getSysRoles();
//        List roleName = new ArrayList();
//        for (SysRole role : roles){
//            roleName.add(role.getEnName());
//        }
//
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.addRoles(roleName);
//        return simpleAuthorizationInfo;
        return null;
    }
//认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String account = (String) authenticationToken.getPrincipal();
        SysUser sysUser = sysUserMapper.selectByAccount(account);
        if (sysUser == null) {
            return null;
        }else if (sysUser.getLoginFlag() == 0){
            throw new DisabledAccountException();       //账户禁用异常
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getUserPwd(), getName());
        return info;
    }
}
