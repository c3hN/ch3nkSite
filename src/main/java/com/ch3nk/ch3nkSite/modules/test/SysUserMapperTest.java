package com.ch3nk.ch3nkSite.modules.test;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SysUserMapperTest {

    public SqlSessionFactory sqlSessionFactory;
    public SqlSession session;

    @Before
    public void before() throws IOException {
        String resource = "test/sqlMapperConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
    }
    @After
    public void after() {
        session.close();
    }

    @Test
    public void test() throws IOException {
        SysRole role1 = new SysRole();
        SysRole role2 = new SysRole();
        SysRole role3 = new SysRole();
        role1.setRoleId("role111111111");
        role2.setRoleId("role222222222");
        role3.setRoleId("role333333333");


        List<SysRole> list = new ArrayList<SysRole>();
        list.add(role1);
        list.add(role2);
        list.add(role3);

        SysUser sysUser = new SysUser();
        sysUser.setUserId("fa5a3a6b173ddb4f563c36a5e1387668");
        sysUser.setSysRoles(list);



        session = sqlSessionFactory.openSession();
        SysUserMapper sysUserMapper = session.getMapper(SysUserMapper.class);
        int res = sysUserMapper.addUserRole(sysUser);
        System.out.println("===================================");
        System.out.println(res);
        System.out.println("===================================");
    }

}
