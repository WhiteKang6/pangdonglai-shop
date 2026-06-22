package com.database.homework.pangdonglaishop.service;

import com.database.homework.pangdonglaishop.DTO.OrderCreateDTO;
import com.database.homework.pangdonglaishop.DTO.OrderPageQueryDTO;
import com.database.homework.pangdonglaishop.VO.OrderVO;
import com.database.homework.pangdonglaishop.VO.PageResult;

public interface OrderService {
    void add(OrderCreateDTO orderCreateDTO);

    void delete(Long orderId);

    PageResult<OrderVO> page(OrderPageQueryDTO orderPageQueryDTO);

    OrderVO detail(Long orderId);
}
