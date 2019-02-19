package mapper_test;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccount;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysAccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class SysAccountMapperTest {

    public SqlSessionFactory sqlSessionFactory;
    public SqlSession session;
    @Before
    public void before() throws IOException {
        String resource = "test/sqlMapperConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
    }
    @After
    public void after() {
        session.close();
    }
    @Test
    public void test() {
        SysAccountMapper mapper = session.getMapper(SysAccountMapper.class);
        SysAccount sysAccount = mapper.selectByAccount("18913339867");
        System.out.println(sysAccount);
    }
}
