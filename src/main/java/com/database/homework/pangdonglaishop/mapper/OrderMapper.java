package com.database.homework.pangdonglaishop.mapper;

import com.database.homework.pangdonglaishop.DTO.OrderPageQueryDTO;
import com.database.homework.pangdonglaishop.VO.OrderVO;
import com.database.homework.pangdonglaishop.entity.Order;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {

    void insert(Order order);

    @Delete("delete from [order] where id = #{id}")
    void deleteById(Long id);

    Page<OrderVO> selectPage(OrderPageQueryDTO orderPageQueryDTO);

    @Select("select [order].*,[user].name as operatorName from [order] left join [user] on [order].operator_id = [user].id where [order].id = #{orderId}")
    OrderVO selectById(Long orderId);

    @Select("select count(*) from [order] where operator_id = #{userId}")
    int selectByUserId(Long userId);
}
