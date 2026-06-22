package com.database.homework.pangdonglaishop.service;

import com.database.homework.pangdonglaishop.DTO.ProductCreateDTO;
import com.database.homework.pangdonglaishop.DTO.ProductPageQueryDTO;
import com.database.homework.pangdonglaishop.DTO.ProductUpdateDTO;
import com.database.homework.pangdonglaishop.VO.PageResult;
import com.database.homework.pangdonglaishop.VO.ProductVO;

public interface ProductService {
    void add(ProductCreateDTO productCreateDTO);

    void update(ProductUpdateDTO productUpdateDTO);

    void delete(Long id);

    PageResult<ProductVO> page(ProductPageQueryDTO productPageQueryDTO);

    ProductVO getById(Long id);

    void updateStatus(Long id, Integer status);
}
