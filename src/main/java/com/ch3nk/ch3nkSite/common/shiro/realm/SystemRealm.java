package com.ch3nk.ch3nkSite.common.shiro.realm;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccount;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysAccountMapper;
import com.ch3nk.ch3nkSite.modules.sys.service.SysAccountService;
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
    @Autowired
    private SysAccountService sysAccountService;

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
        SysAccount account = (SysAccount) principalCollection.getPrimaryPrincipal();
        if (account.getAccount().equals(adminAccount)) {
            List<String> permission = new ArrayList<>();
            permission.add("*:*");
            info= new SimpleAuthorizationInfo();
            info.addStringPermissions(permission);
            return info;
        }else {
            List<String> accountCodes = sysAccountService.findAccountCodes(account.getAcId());
            if (accountCodes.size() != 0) {
                info = new SimpleAuthorizationInfo();
                info.addStringPermissions(accountCodes);
                return info;
            }
        }
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
