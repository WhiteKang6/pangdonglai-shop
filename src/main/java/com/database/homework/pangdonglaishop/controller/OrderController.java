package com.database.homework.pangdonglaishop.controller;

import com.database.homework.pangdonglaishop.DTO.OrderCreateDTO;
import com.database.homework.pangdonglaishop.DTO.OrderPageQueryDTO;
import com.database.homework.pangdonglaishop.VO.OrderVO;
import com.database.homework.pangdonglaishop.VO.PageResult;
import com.database.homework.pangdonglaishop.VO.Result;
import com.database.homework.pangdonglaishop.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
@Tag(name = "订单接口")
public class OrderController {
    @Autowired
    private OrderService orderService;



    /**
     * 新建订单
     * @param orderCreateDTO
     * @return
     */
    @PostMapping("/add")
    @Operation(summary="新建订单")
    public Result add(@RequestBody OrderCreateDTO orderCreateDTO) {
        log.info("新建订单：{}", orderCreateDTO);
        orderService.add(orderCreateDTO);
        return Result.success();
    }

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    @PostMapping("/delete")
    @Operation(summary="删除订单")
    public Result delete(Long orderId) {
        log.info("删除订单：{}", orderId);
        orderService.delete(orderId);
        return Result.success();
    }

    /**
     * 订单分页查询
     * @param orderPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(summary="订单分页查询")
    public Result<PageResult<OrderVO>> page(OrderPageQueryDTO orderPageQueryDTO){
        log.info("订单分页查询：{}", orderPageQueryDTO);
        return Result.success(orderService.page(orderPageQueryDTO));
    }


    /**
     * 订单详情查询
     * @param orderId
     * @return
     */
    @GetMapping("/detail/{orderId}")
    @Operation(summary="订单详情查询")
    public Result<OrderVO> detail(@PathVariable Long orderId){
        log.info("订单详情查询：{}", orderId);
        return Result.success(orderService.detail(orderId));
    }





}
