package com.database.homework.pangdonglaishop.controller;

import com.database.homework.pangdonglaishop.DTO.CategoryCreateDTO;
import com.database.homework.pangdonglaishop.DTO.CategoryUpdateDTO;
import com.database.homework.pangdonglaishop.VO.PageResult;
import com.database.homework.pangdonglaishop.VO.Result;
import com.database.homework.pangdonglaishop.entity.Category;
import com.database.homework.pangdonglaishop.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@Tag(name="分类模块")
@Slf4j
public class CategoryController {





    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param categoryCreateDTO
     * @return
     */
    @Operation(summary="新增分类")
    @PostMapping("/add")
    public Result add(@RequestBody CategoryCreateDTO categoryCreateDTO){
        log.info("新增分类:{}",categoryCreateDTO);
        categoryService.add(categoryCreateDTO);
        return Result.success();
    }

    /**
     * 更新分类
     * @param categoryUpdateDTO
     * @return
     */
    @Operation(summary="更新分类")
    @PostMapping("/update")
    public Result update(@RequestBody CategoryUpdateDTO categoryUpdateDTO){
        log.info("更新分类:{}",categoryUpdateDTO);
        categoryService.update(categoryUpdateDTO);
        return Result.success();
    }


    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary="删除分类")
    public Result delete(@PathVariable Long id){
        log.info("删除分类:{}",id);
        categoryService.delete(id);
        return Result.success();
    }

    /**
     * 分类查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Operation(summary="分类查询")
    @GetMapping("/page")
    public Result<PageResult<Category>> page(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        log.info("分类查询:{}",pageNum,pageSize);
        PageResult<Category> pageResult = categoryService.page(pageNum, pageSize);
        return Result.success(pageResult);
    }

}
