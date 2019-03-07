package com.ch3nk.ch3nkSite.modules.test;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysRoleMenu;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysRoleMapper;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysUserMapper;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysRoleMenuMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SysRoleMapperTest {


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
    public void test_1() {
        session = sqlSessionFactory.openSession();
        String roleId = "236d65b7a6de45b9a3baa3be840672da";
        String[] menuIds = {"fe558d9c8d444c8b8d50e3ef0914f916","f8545a62e9f24c0e8a28e9d074ad074c","dfbb212da9c945e3a7ba26b4dbc40d80"};
        com.ch3nk.ch3nkSite.modules.sys.mappers.SysRoleMapper mapper = session.getMapper(com.ch3nk.ch3nkSite.modules.sys.mappers.SysRoleMapper.class);
//
//        int i = mapper.insertRoleMenus(roleId, menuIds);
//        int j = mapper.deleteRoleMenus("e945b0d9863b4f5cb8e1799903fe50cf");

        SysRoleMenuMapper mapper1 = session.getMapper(SysRoleMenuMapper.class);
        List<SysRoleMenu> sysRoleMenus = mapper1.selectRoleMenus(roleId);
//        System.out.println(i);
//        System.out.println(j);
//        System.out.println(sysRoleMenus);
    }






}
