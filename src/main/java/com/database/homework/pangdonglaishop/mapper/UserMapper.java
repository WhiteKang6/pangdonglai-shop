package com.database.homework.pangdonglaishop.mapper;

import com.database.homework.pangdonglaishop.DTO.UserPageQueryDTO;
import com.database.homework.pangdonglaishop.VO.UserVO;
import com.database.homework.pangdonglaishop.entity.User;
import com.github.pagehelper.Page;
import jakarta.validation.constraints.NotBlank;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from [user] where username = #{username}")
    User selectByUsername(String username);

    @Insert("insert into [user] (username, password, name,age,sex,status, create_time) " +
            "values (#{username}, #{password}, #{name},#{age},#{sex}, #{status}, #{createTime})")
    void insert(User user);

    void updateById(User user);

    Page<UserVO> selectPage(UserPageQueryDTO userPageQueryDTO);

    @Select("select * from [user] where id = #{id}")
    User selectById(Long id);

    @Update("update [user] set password = #{newPassword} where id = #{id}")
    void updatePwd(Long id, @NotBlank(message = "新密码不能为空") String newPassword);

    @Delete("delete from [user] where id = #{id}")
    void deleteById(Long id);
}
