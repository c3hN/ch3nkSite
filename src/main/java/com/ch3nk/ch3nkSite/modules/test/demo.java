package com.ch3nk.ch3nkSite.modules.test;


import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccount;
import com.ch3nk.ch3nkSite.modules.sys.service.SysAccountService;
import com.ch3nk.ch3nkSite.modules.sys.service.SysUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class demo {


    @Test
    public void test() {
        String md51 = new SimpleHash("md5", "123", null, 1).toHex();
        String md52 = new SimpleHash("md5", "123", "", 1).toHex();
        String md53 = new SimpleHash("md5", "123").toHex();
        System.out.println(md51);
        System.out.println(md52);
        System.out.println(md53);

    }
}
