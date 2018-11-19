package net.togogo.mapper;

import java.util.List;
import net.togogo.bean.Application;
import net.togogo.bean.ApplicationExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ApplicationMapper {
    int countByExample(ApplicationExample example);

    int deleteByExample(ApplicationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Application record);

    int insertSelective(Application record);

    @Select("select * from t_application where user_id=#{user_id}")
    List<Application> selectbyuserid(int user_id);

    @Select("select * from t_application where state=1 and user_id=#{user_id}")
    List<Application> selectbystatus(int user_id);

    List<Application> selectByExample(ApplicationExample example);

    Application selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Application record, @Param("example") ApplicationExample example);

    int updateByExample(@Param("record") Application record, @Param("example") ApplicationExample example);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);
}