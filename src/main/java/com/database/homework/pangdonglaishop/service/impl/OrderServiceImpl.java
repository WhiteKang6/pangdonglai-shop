package com.database.homework.pangdonglaishop.service.impl;

import com.database.homework.pangdonglaishop.DTO.OrderCreateDTO;
import com.database.homework.pangdonglaishop.DTO.OrderPageQueryDTO;
import com.database.homework.pangdonglaishop.VO.OrderItemVO;
import com.database.homework.pangdonglaishop.VO.OrderVO;
import com.database.homework.pangdonglaishop.VO.PageResult;
import com.database.homework.pangdonglaishop.entity.Order;
import com.database.homework.pangdonglaishop.entity.OrderItem;
import com.database.homework.pangdonglaishop.entity.Product;
import com.database.homework.pangdonglaishop.exception.BaseException;
import com.database.homework.pangdonglaishop.mapper.OrderItemMapper;
import com.database.homework.pangdonglaishop.mapper.OrderMapper;
import com.database.homework.pangdonglaishop.mapper.ProductMapper;
import com.database.homework.pangdonglaishop.service.OrderService;
import com.database.homework.pangdonglaishop.util.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderMapper orderMapper;




    @Override
    @Transactional
    public void add(OrderCreateDTO orderCreateDTO) {
        //先创建订单
        Order order = new Order();
        //订单号
        order.setOrderSn(String.valueOf(System.currentTimeMillis()));
        //订单金额
        BigDecimal totalAmount=BigDecimal.ZERO;
        //创建订单商品列表
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderCreateDTO.OrderItemDTO item : orderCreateDTO.getItems()) {
            //查询商品是否存在,且状态为上架
            Product product = productMapper.selectById(item.getProductId());
            if(product==null||product.getStatus()!=1){
                throw new BaseException("商品状态异常");
            }
            //判断商品库存是否足够
            if(product.getStock()<item.getCount()){
                throw new BaseException("商品库存不足");
            }
            //更新库存
            productMapper.update(Product.builder().id(product.getId()).stock(product.getStock()-item.getCount()).build());
            totalAmount=totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(item.getCount())));
            OrderItem orderItem = new OrderItem();
            BeanUtils.copyProperties(item, orderItem);
            orderItem.setPrice(product.getPrice());
            orderItems.add(orderItem);
        }
        order.setTotalAmount(totalAmount);
        //关联用户的id
        order.setOperatorId(ThreadLocalUtil.get());
        //创建时间
        order.setCreateTime(LocalDateTime.now());
        orderMapper.insert(order);
        //再创建订单商品,批量创建
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(order.getId());
        }
        orderItemMapper.insertBatch(orderItems);


    }

    @Override
    @Transactional
    public void delete(Long orderId) {
        //先删除订单商品
        orderItemMapper.deleteByOrderId(orderId);
        //再删除订单
        orderMapper.deleteById(orderId);
    }

    @Override
    public PageResult<OrderVO> page(OrderPageQueryDTO orderPageQueryDTO) {
        PageHelper.startPage(orderPageQueryDTO.getPageNum(), orderPageQueryDTO.getPageSize());
        Page<OrderVO> page = orderMapper.selectPage(orderPageQueryDTO);

        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    public OrderVO detail(Long orderId) {
        OrderVO orderVO =orderMapper.selectById(orderId);

        List<OrderItemVO> items = orderItemMapper.selectByOrderId(orderId);
        orderVO.setItems(items);
        return orderVO;
    }
}
