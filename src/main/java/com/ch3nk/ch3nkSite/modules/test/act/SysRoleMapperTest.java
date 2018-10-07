package com.ch3nk.ch3nkSite.modules.test.act;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;
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
        SysRoleMapper mapper = session.getMapper(SysRoleMapper.class);
//        String roleId = "12333";
////        String[] menuIds = {"7167485cc3674a3b8eb74c4c90a079e6","84b3be1a0225460abfe2b7675da9a428","45e9d87bda3b4286b81ba3e3e4c786a9","1955871319434c799805bcbf47eaa643"};
//        String[] menuIds = {};
//        mapper.insertRoleMenus(roleId,menuIds);
//        SysRole sysRole = mapper.selectByPrimaryKey("42b62063406142d4973dae92451a61b9");
//        System.out.println(sysRole);
        SysDepartment department = new SysDepartment();
        department.setDeptId("b426ed9f77814055b35280fde138fc3d");
        SysRole sysRole = new SysRole();
        sysRole.setDepartment(department);
        sysRole.setDeleteFlag("1");
        List<SysRole> list = mapper.selectBy(sysRole);
        System.out.println(list);
    }






}
