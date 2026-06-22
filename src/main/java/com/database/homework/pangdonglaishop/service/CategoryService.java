package com.database.homework.pangdonglaishop.service;

import com.database.homework.pangdonglaishop.DTO.CategoryCreateDTO;
import com.database.homework.pangdonglaishop.DTO.CategoryUpdateDTO;
import com.database.homework.pangdonglaishop.VO.PageResult;
import com.database.homework.pangdonglaishop.entity.Category;

public interface CategoryService {
    void add(CategoryCreateDTO categoryCreateDTO);

    void update(CategoryUpdateDTO categoryUpdateDTO);

    void delete(Long id);

    PageResult<Category> page(Integer pageNum, Integer pageSize);
}
