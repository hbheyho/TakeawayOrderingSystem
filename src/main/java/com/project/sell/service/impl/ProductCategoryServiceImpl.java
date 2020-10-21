package com.project.sell.service.impl;

import com.project.sell.dataobject.ProductCategory;
import com.project.sell.repository.ProductCategoryRepository;
import com.project.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HB
 * @Description: ProductCategory service 接口实现类
 * @CreateDate: 22:52 2020/10/21
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /**
     * @Author: HB
     * @Description:  根据categoryId查询类目
     * @Date: 22:53 2020/10/21
     * @Params: categoryId
     * @Returns: ProductCategory
    */
    @Override
    public ProductCategory findOne(Integer categoryId) {

        return productCategoryRepository.findOne(categoryId);

    }

    /**
     * @Author: HB
     * @Description: 查询所有类目
     * @Date: 22:53 2020/10/21
     * @Params: null
     * @Returns: List<ProductCategory>
    */
    @Override
    public List<ProductCategory> findAll() {

        return productCategoryRepository.findAll();

    }

    /**
     * @Author: HB
     * @Description: 根据categoryType查询类目
     * @Date: 22:54 2020/10/21
     * @Params: categoryTypeList
     * @Returns: ProductCategory
    */
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {

        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);

    }

    /**
     * @Author: HB
     * @Description: 新增类目
     * @Date: 22:54 2020/10/21
     * @Params: productCategory
     * @Returns: ProductCategory
    */
    @Override
    public ProductCategory save(ProductCategory productCategory) {

        return productCategoryRepository.save(productCategory);

    }
}
