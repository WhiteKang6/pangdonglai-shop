package com.database.homework.pangdonglaishop.mapper;

import com.database.homework.pangdonglaishop.VO.OrderItemVO;
import com.database.homework.pangdonglaishop.entity.OrderItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    void insertBatch(List<OrderItem> orderItems);

    @Delete("delete from order_item where order_id = #{orderId}")
    void deleteByOrderId(Long orderId);

    @Select("select o.*,p.name as productName from order_item o left join product p on o.product_id = p.id where order_id = #{orderId}")
    List<OrderItemVO> selectByOrderId(Long orderId);

    @Select("select count(*) from order_item where product_id = #{id}")
    int selectByProductId(Long id);
}
