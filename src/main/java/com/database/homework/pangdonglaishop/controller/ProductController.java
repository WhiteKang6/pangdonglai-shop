package com.database.homework.pangdonglaishop.controller;

import com.database.homework.pangdonglaishop.DTO.ProductCreateDTO;
import com.database.homework.pangdonglaishop.DTO.ProductPageQueryDTO;
import com.database.homework.pangdonglaishop.DTO.ProductUpdateDTO;
import com.database.homework.pangdonglaishop.VO.PageResult;
import com.database.homework.pangdonglaishop.VO.ProductVO;
import com.database.homework.pangdonglaishop.VO.Result;
import com.database.homework.pangdonglaishop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Tag(name="商品模块")
@Slf4j
public class ProductController {


    @Autowired
    private ProductService productService;

    /**
     * 新增商品
     * @param productCreateDTO
     * @return
     */
    @PostMapping("/add")
    @Operation(summary="新增商品")
    public Result add(@RequestBody ProductCreateDTO productCreateDTO) {
        log.info("新增商品:{}", productCreateDTO);
        productService.add(productCreateDTO);
        return Result.success();
    }

    /**
     * 更新商品
     * @param productUpdateDTO
     * @return
     */
    @PutMapping("/update")
    @Operation(summary="更新商品")
    public Result update(@RequestBody ProductUpdateDTO productUpdateDTO) {
        log.info("更新商品:{}", productUpdateDTO);
        productService.update(productUpdateDTO);
        return Result.success();
    }

    /**
     * 更新商品状态
     * @param id
     * @param status
     * @return
     */
    @PutMapping("/updateStatus")
    @Operation(summary="更新商品状态")
    public Result updateStatus(@RequestParam Long id,@RequestParam Integer status) {
        log.info("更新商品状态:{}", id, status);
        productService.updateStatus(id, status);
        return Result.success();
    }


    /**
     * 删除商品
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary="删除商品")
    public Result delete(@PathVariable Long id) {
        log.info("删除商品:{}", id);
        productService.delete(id);
        return Result.success();
    }

    /**
     * 分页查询商品
     * @param productPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(summary="分页查询商品")
    public Result<PageResult<ProductVO>> page( ProductPageQueryDTO productPageQueryDTO) {

        log.info("分页查询商品:{}", productPageQueryDTO);
        return Result.success(productService.page(productPageQueryDTO));
    }


    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    @Operation(summary="根据id查询商品")
    public Result<ProductVO> detail(@PathVariable Long id) {
        log.info("根据id查询商品:{}", id);
        return Result.success(productService.getById(id));
    }



}