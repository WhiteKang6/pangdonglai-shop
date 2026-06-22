package com.database.homework.pangdonglaishop.service.impl;

import com.database.homework.pangdonglaishop.DTO.ProductCreateDTO;
import com.database.homework.pangdonglaishop.DTO.ProductPageQueryDTO;
import com.database.homework.pangdonglaishop.DTO.ProductUpdateDTO;
import com.database.homework.pangdonglaishop.VO.PageResult;
import com.database.homework.pangdonglaishop.VO.ProductVO;
import com.database.homework.pangdonglaishop.entity.Category;

import com.database.homework.pangdonglaishop.entity.Product;
import com.database.homework.pangdonglaishop.exception.BaseException;
import com.database.homework.pangdonglaishop.mapper.CategoryMapper;

import com.database.homework.pangdonglaishop.mapper.OrderItemMapper;
import com.database.homework.pangdonglaishop.mapper.ProductMapper;
import com.database.homework.pangdonglaishop.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ProductServiceImply implements ProductService {


    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;


    @Override
    public void add(ProductCreateDTO productCreateDTO) {
        //分类要存在
        Category category = categoryMapper.selectById(productCreateDTO.getCategoryId());
        if(category == null ){
            throw new BaseException("分类不存在");
        }
        //商品名称不能重复
        Product productByName = productMapper.selectByName(productCreateDTO.getName());
        if(productByName != null){
            throw new BaseException("商品名称已存在");
        }
        Product product = new Product();
        product.setName(productCreateDTO.getName());
        product.setPrice(productCreateDTO.getPrice());
        product.setStock(productCreateDTO.getStock());
        product.setCategoryId(productCreateDTO.getCategoryId());
        product.setImageUrl(productCreateDTO.getImageUrl());
        product.setStatus(1);
        product.setCreateTime(LocalDateTime.now());
        productMapper.insert(product);

    }

    @Override
    public void update(ProductUpdateDTO productUpdateDTO) {
        //查找 商品名是否存在重复
        Product productByName = productMapper.selectByNameAndId(productUpdateDTO.getName(),productUpdateDTO.getId());
        if(productByName != null){
            throw new BaseException("商品名称已存在");
        }
        Product product = new Product();
        BeanUtils.copyProperties(productUpdateDTO,product);
        productMapper.update(product);
    }

    @Override
    public void delete(Long id) {
        //如果商品下有订单，不能删除商品
        if(orderItemMapper.selectByProductId(id)>0) {
            throw new BaseException("商品下有订单，不能删除商品");
        }
        productMapper.deleteById(id);

    }

    @Override
    public PageResult<ProductVO> page(ProductPageQueryDTO productPageQueryDTO) {
        PageHelper.startPage(productPageQueryDTO.getPageNum(), productPageQueryDTO.getPageSize());
        Page<ProductVO> page = productMapper.selectPage(productPageQueryDTO);
        return new PageResult<>(page.getTotal(), page.getResult());

    }

    @Override
    public ProductVO getById(Long id) {
        ProductVO productVO = productMapper.selectProductVOById(id);
        if(productVO == null){
            throw new BaseException("商品不存在");
        }
        return productVO;
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        product.setStatus(status);
        productMapper.update(product);
    }
}
