package com.project.sell.service;

import com.project.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @Author: HB
 * @Description: ProductCategory service 接口类
 * @CreateDate: 22:48 2020/10/21
 */

public interface ProductCategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
