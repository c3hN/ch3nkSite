package com.ch3nk.ch3nkSite.common.shiro.realm;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccount;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysAccountMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

public class SystemRealm extends AuthorizingRealm {

    @Autowired
    private SysAccountMapper accountMapper;

    @Value("${admin.account}")
    private String adminAccount;
    @Value("${admin.userPwd}")
    private String adminPwd;
    @Value("${shiro.hashAlgorithmName}")
    private String hashAlgorithmName;
    @Value("${shiro.hashIterations}")
    private String hashIterations;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = null;
//        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        List<String> permission = new ArrayList<>();
//        if (sysUser.getAccount().equals(adminAccount)) {
//            String pwd = new SimpleHash(hashAlgorithmName, adminPwd).toHex();
//            if (sysUser.getUserPwd().equals(pwd)) {
//                permission.add("*:*");
//                info.addStringPermissions(permission);
//                return info;
//            }
//        }
//        else {
//            List<SysRole> list = sysUserMapper.selectRolesForUser(sysUser.getUserId());
//            if (list.size() > 0) {
//                String[] roleIds = new String[list.size()];
//                for (int i = 0; i < list.size(); i++) {
//                    roleIds[i] = list.get(i).getRoleId();
//                }
//                List<SysMenu> sysMenus = sysRoleMapper.selectMenusForRoles(roleIds);
//                for (SysMenu m : sysMenus) {
//                    permission.add(m.getPermission());
//                }
//                info.addStringPermissions(permission);
//                return info;
//            }
//
//        }
        info = new SimpleAuthorizationInfo();
        List<String> permission = new ArrayList<>();
        permission.add("*:*");
        info.addStringPermissions(permission);
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String account = (String) authenticationToken.getPrincipal();
//        SysUser sysUser = sysUserMapper.selectByAccount(account);
//        if (sysUser == null) {
//            return null;
//        } else if (StringUtils.equals(sysUser.getLoginFlag(), "0")) {
//            throw new DisabledAccountException();
//        }
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getUserPwd(), getName());
//        return info;
        SimpleAuthenticationInfo info = null;
        String account = (String) authenticationToken.getPrincipal();
        SysAccount sysAccount = accountMapper.selectByAccount(account);
        if (sysAccount != null) {
            info = new SimpleAuthenticationInfo(sysAccount, sysAccount.getPassword(), getName());
        }
        return info;
    }
}
