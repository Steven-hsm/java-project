package com.hsm.bootmybatis.mapper;

import com.hsm.bootmybatis.entity.po.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/8/4 15:38
 */
//@Mapper
public interface UserMapper {
    //@Select("SELECT * FROM USER WHERE NAME = #{name} Limit 1")
    UserPO findByName(@Param("name") String name);

    //@Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);
}
