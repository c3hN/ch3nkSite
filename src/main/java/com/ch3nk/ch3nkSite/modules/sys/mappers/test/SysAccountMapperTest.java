package com.ch3nk.ch3nkSite.modules.sys.mappers.test;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccount;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysAccountMapper;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysUserMapper;
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

public class SysAccountMapperTest {
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
    public void test() {
        session = sqlSessionFactory.openSession();
        SysAccountMapper mapper = session.getMapper(SysAccountMapper.class);
        SysAccount sysAccount = new SysAccount();
        sysAccount.setNickName("11");
        List<SysAccount> sysAccounts = mapper.selectByPage(sysAccount, 0, 10);
        System.out.println(sysAccount);
    }
}
