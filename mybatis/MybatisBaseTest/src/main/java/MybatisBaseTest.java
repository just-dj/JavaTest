import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @Author justdj
 * @create 2020/2/26 10:46 上午
 */

public class MybatisBaseTest {

    public static void main(String[] args) throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //基于xml的配置
        SqlSession session = sqlSessionFactory.openSession();
        Map result = session.selectOne("mapper.UserMapper.selectUser", 1);
        System.out.println(result);



      UserMapper mapper = session.getMapper(UserMapper.class);
//      String result = mapper.selectUser(1);

        }
}
