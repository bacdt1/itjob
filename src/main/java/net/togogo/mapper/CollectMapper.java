package net.togogo.mapper;

import java.util.List;
import net.togogo.bean.Collect;
import net.togogo.bean.CollectExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CollectMapper {

    @Select("select * from t_collect where user_id=#{user_id}")
    List<Collect> selectbyuserid(int user_id);

    int countByExample(CollectExample example);

    int deleteByExample(CollectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    List<Collect> selectByExample(CollectExample example);

    Collect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Collect record, @Param("example") CollectExample example);

    int updateByExample(@Param("record") Collect record, @Param("example") CollectExample example);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}