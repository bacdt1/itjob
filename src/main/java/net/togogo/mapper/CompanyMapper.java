package net.togogo.mapper;

import java.util.List;
import net.togogo.bean.Company;
import net.togogo.bean.CompanyExample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CompanyMapper {

    @Select("select * from t_company where username=#{username} ")
    Company selectbyusername(String username);



    int countByExample(CompanyExample example);

    int deleteByExample(CompanyExample example);

    int deleteByPrimaryKey(Integer id);


    @Insert("insert into t_company(username,password,email) values(#{username},#{password},#{email})")
    int insert(Company record);

    @Select("select * from t_company where email=#{email} ")
    Company selectbyemail(String email);

    int insertSelective(Company record);

    List<Company> selectByExample(CompanyExample example);

    @Select("select * from t_company where id=#{id}")
    Company selectByPrimaryKey(int id);

    int updateByExampleSelective(@Param("record") Company record, @Param("example") CompanyExample example);

    int updateByExample(@Param("record") Company record, @Param("example") CompanyExample example);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
}