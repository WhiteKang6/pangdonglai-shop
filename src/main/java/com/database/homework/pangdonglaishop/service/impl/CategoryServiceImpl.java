package com.database.homework.pangdonglaishop.service.impl;

import com.database.homework.pangdonglaishop.DTO.CategoryCreateDTO;
import com.database.homework.pangdonglaishop.DTO.CategoryUpdateDTO;
import com.database.homework.pangdonglaishop.VO.PageResult;
import com.database.homework.pangdonglaishop.entity.Category;
import com.database.homework.pangdonglaishop.exception.BaseException;
import com.database.homework.pangdonglaishop.mapper.CategoryMapper;
import com.database.homework.pangdonglaishop.mapper.ProductMapper;
import com.database.homework.pangdonglaishop.service.CategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;




    @Override
    public void add(CategoryCreateDTO categoryCreateDTO) {

        Category category = categoryMapper.selectByName(categoryCreateDTO.getName());
        if(category!=null){
            throw new IllegalArgumentException("分类名称已存在");
        }
        category = new Category();
        category.setName(categoryCreateDTO.getName());
        category.setCreateTime(LocalDateTime.now());
        categoryMapper.insert(category);
    }

    public void update(CategoryUpdateDTO categoryUpdateDTO) {

        //查找 分类名是否存在重复
        Category category = categoryMapper.selectByNameAndId(categoryUpdateDTO.getName(),categoryUpdateDTO.getId());
        if(category!=null){
            throw new BaseException("分类名称已存在");
        }
        category=new Category();
        category.setId(categoryUpdateDTO.getId());
        category.setName(categoryUpdateDTO.getName());

        categoryMapper.update(category);
    }

    @Override
    public void delete(Long id) {
        //如果分类下有商品，不能删除分类
        if(productMapper.selectByCategoryId(id)>0){
            throw new BaseException("分类下有商品，不能删除分类");
        }
        categoryMapper.delete(id);
    }

    @Override
    public PageResult<Category> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Category> page= categoryMapper.page();

        return new PageResult<>(page.getTotal(),page.getResult());
    }
}
