package com.database.homework.pangdonglaishop.mapper;

import com.database.homework.pangdonglaishop.DTO.ProductPageQueryDTO;
import com.database.homework.pangdonglaishop.DTO.ProductUpdateDTO;
import com.database.homework.pangdonglaishop.VO.ProductVO;
import com.database.homework.pangdonglaishop.entity.Product;
import com.github.pagehelper.Page;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.Collection;

@Mapper
public interface ProductMapper {
    @Select("select * from product where name = #{name}")
    Product selectByName(String name);

    @Select("select * from product where name = #{name} and id != #{id}")
    Product selectByNameAndId(String name, Long id);

    @Insert("insert into product (name, price, stock, status, category_id, create_time, image_url) " +
            "values (#{name},#{price},#{stock},#{status},#{categoryId},#{createTime},#{imageUrl})")
    void insert(Product product);

    void update(Product product);

    @Delete("delete from product where id= #{id}")
    void deleteById(Long id);

    Page<ProductVO> selectPage(ProductPageQueryDTO productPageQueryDTO);

    @Select("select product.*, category.name as categoryName" +
            " from product left outer join category on product.category_id = category.id where product.id = #{id}")
    ProductVO selectProductVOById(Long id);



    @Select("select * from product where id = #{productId}")
    Product selectById(@NotNull(message = "商品ID不能为空") Long productId);

    @Select("select count(*) from product where category_id = #{categoryId}")
    int selectByCategoryId(Long categoryId);
}
