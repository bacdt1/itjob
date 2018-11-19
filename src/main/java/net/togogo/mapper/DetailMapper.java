package net.togogo.mapper;

import java.util.List;
import net.togogo.bean.Detail;
import net.togogo.bean.DetailExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DetailMapper {
    int countByExample(DetailExample example);

    int deleteByExample(DetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Detail record);

    int insertSelective(Detail record);

    List<Detail> selectByExample(DetailExample example);

    @Select("select * from t_detail where id = #{id}")
    Detail selectbyid(int id);


    Detail selectByPrimaryKey(int id);

    int updateByExampleSelective(@Param("record") Detail record, @Param("example") DetailExample example);

    int updateByExample(@Param("record") Detail record, @Param("example") DetailExample example);

    int updateByPrimaryKeySelective(Detail record);

    int updateByPrimaryKey(Detail record);
}