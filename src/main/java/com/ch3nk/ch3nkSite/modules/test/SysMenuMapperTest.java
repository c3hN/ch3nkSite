//package com.ch3nk.ch3nkSite.modules.test;
//
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
//import com.ch3nk.ch3nkSite.modules.sys.mapper.SysMenuMapper;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//public class SysMenuMapperTest {
//
//
//    public SqlSessionFactory sqlSessionFactory;
//    public SqlSession session;
//
//    @Before
//    public void before() throws IOException {
//        String resource = "test/sqlMapperConfig.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
//    }
//    @After
//    public void after() {
//        session.close();
//    }
//
//
//    @Test
//    public void test_1() {
//        SysMenu sysMenu = new SysMenu();
//        sysMenu.setCategory("0");
//        sysMenu.setDeleteFlag("1");
//        session= session = sqlSessionFactory.openSession();
//        SysMenuMapper mapper = session.getMapper(SysMenuMapper.class);
//        int i = mapper.selectCountSelective(sysMenu);
//        System.out.println(i);
//
//    }
//
//
//
//
//
//
//}
