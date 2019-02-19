//package com.ch3nk.ch3nkSite.modules.test;
//
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysOperation;
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
//import com.ch3nk.ch3nkSite.modules.sys.mapper.SysOperationMapper;
//import com.ch3nk.ch3nkSite.modules.sys.mapper.SysUserMapper;
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
//import java.util.Date;
//
//public class SysOperationMapperTest {
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
//        session= session = sqlSessionFactory.openSession();
//        SysOperationMapper mapper = session.getMapper(SysOperationMapper.class);
//        SysOperation sysOperation = new SysOperation();
//        sysOperation.setReqIP("1111111111111111111");
//        sysOperation.setId("idididididid");
//        sysOperation.setBrowserName("chrome");
//        sysOperation.setBrowserVersion("1.0");
//        sysOperation.setClientType("windows-10");
//        sysOperation.setCreateDate(new Date());
//        mapper.insertSelective(sysOperation);
//    }
//
//
//
//
//
//
//}
