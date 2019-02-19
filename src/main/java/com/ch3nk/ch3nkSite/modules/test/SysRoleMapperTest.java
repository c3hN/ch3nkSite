package com.ch3nk.ch3nkSite.modules.test;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysRoleMapper;
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
        String roleId = "e945b0d9863b4f5cb8e1799903fe50cf";
//        String[] menuIds = {"fe558d9c8d444c8b8d50e3ef0914f916","f8545a62e9f24c0e8a28e9d074ad074c","dfbb212da9c945e3a7ba26b4dbc40d80"};
        SysRoleMapper mapper = session.getMapper(SysRoleMapper.class);
//        SysRole sysRole = new SysRole();
//        sysRole.setDeleteFlag("1");
//        List<SysRole> list = mapper.selectByPage(sysRole, 0, 20);
//        System.out.println(list);
        List<SysMenu> sysMenus = mapper.selectMenusForRole(roleId);
        System.out.println(sysMenus);
    }






}
