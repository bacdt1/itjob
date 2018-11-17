package net.togogo.mapper;

import java.util.List;
import net.togogo.bean.User;
import net.togogo.bean.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where id=#{id}")
    User selectbyid(int id);

    @Select("select * from t_user where username=#{username} ")
    User selectbyusername(String username);

    @Select("select * from t_user where email=#{email} ")
    User selectbyemail(String email);

    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    @Select("insert into t_user(username,password,email) values(#{username},#{password},#{email})")
    Integer insert(User user);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}