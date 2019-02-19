package mapper_test;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccount;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysAccountMapper;
import com.ch3nk.ch3nkSite.modules.sys.mappers.SysMenuMapper;
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

public class SysMenuMapperTest {

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
        SysMenuMapper mapper = session.getMapper(SysMenuMapper.class);
        SysMenu sysMenu = new SysMenu();
        sysMenu.setIsDeleted("0");
        List<SysMenu> sysMenus = mapper.selectBy(sysMenu);
        System.out.println(sysMenus);
    }
}
