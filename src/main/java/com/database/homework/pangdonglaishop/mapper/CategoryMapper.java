package com.database.homework.pangdonglaishop.mapper;

import com.database.homework.pangdonglaishop.entity.Category;
import com.github.pagehelper.Page;
import jakarta.validation.constraints.NotBlank;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CategoryMapper {


    @Insert("insert into category(name,create_time) values(#{name},#{createTime})")
    void insert(Category category);

    @Select("select * from category where name=#{name}")
    Category selectByName(String name);

    @Update("update category set name=#{name} where id=#{id}")
    void update(Category category);

    @Delete("delete from category where id=#{id}")
    void delete(Long id);

    @Select("select * from category")
    Page<Category> page();

    @Select("select * from category where id=#{id}")
    Category selectById(Long id);

    @Select("select * from category where name=#{name} and id!=#{id}")
    Category selectByNameAndId(String name,Long id);
}
