package com.ch3nk.ch3nkSite.modules.test;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysMenuMapper;
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
    public void test_1() {
        SysUser sysUser = new SysUser();
        sysUser.setDeleteFlag("1");
        sysUser.setUserId("234f3486a9f44bf9a98dfbcee379d3d5");
        session= session = sqlSessionFactory.openSession();
        SysUserMapper mapper = session.getMapper(SysUserMapper.class);
        SysDepartment department = new SysDepartment();
        department.setDeptId("11111111111111111");
//        sysUser.setDepartment(department);
        int i = mapper.selectCountBy(sysUser);
        System.out.println(i);
    }






}
