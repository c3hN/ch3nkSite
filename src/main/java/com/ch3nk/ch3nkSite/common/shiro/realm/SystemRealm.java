package com.ch3nk.ch3nkSite.common.shiro.realm;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysUserMapper;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

public class SystemRealm extends AuthorizingRealm {

    @Qualifier("sysUserServiceImpl")
    @Autowired
    private ISysUserService sysUserService;
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
        String nickName = ((SysUser) principalCollection.getPrimaryPrincipal()).getNickName();
        if (StringUtils.equals(nickName,"超级管理员")) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addStringPermission("sysUser:add");
            simpleAuthorizationInfo.addStringPermission("sysUser:import");
            simpleAuthorizationInfo.addStringPermission("sysUser:reportForm");
            simpleAuthorizationInfo.addStringPermission("sysUser:tableSearch");
            simpleAuthorizationInfo.addStringPermission("sysUser:detail");
            simpleAuthorizationInfo.addStringPermission("sysUser:edit");
            simpleAuthorizationInfo.addStringPermission("sysUser:logicalDel");
            return simpleAuthorizationInfo;
        }
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String account = (String) authenticationToken.getPrincipal();
        SysUser user = sysUserService.findByAccount(account);
        if (user == null) {
            return null;
        }else if (StringUtils.equals(user.getDeleteFlag(),"0")){
            throw new DisabledAccountException();       //账户禁用异常
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getUserPwd(), getName());
        return info;

    }
}
