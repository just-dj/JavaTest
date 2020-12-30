package mapper;

import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @Author justdj
 * @create 2020/2/26 11:23 上午
 */
public interface UserMapper {

//    @Select("SELECT * FROM user WHERE id = #{id}")
    Map selectUser(int id);

}
