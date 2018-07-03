package com.ch3nk.ch3nkSite.modules.test;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDic;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysDicMapper;
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

public class SysDicMapperTest {

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
        SysDicMapper mapper = session.getMapper(SysDicMapper.class);
        List<SysDic> sysDicList = mapper.selectParentNodes();
        for (SysDic sysDic : sysDicList) {
            System.out.println(sysDic);
        }
    }

    @Test
    public void test_2() {
        session = sqlSessionFactory.openSession();
        SysDicMapper mapper = session.getMapper(SysDicMapper.class);
        List<SysDic> sysDicList = mapper.selectByParentId("001");
        for (SysDic sysDic : sysDicList) {
            System.out.println(sysDic);
        }
    }
}
