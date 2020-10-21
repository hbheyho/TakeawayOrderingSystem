package com.project.sell.service.impl;

import com.project.sell.dataobject.ProductCategory;
import com.project.sell.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: HB
 * @Description: ProductCategory 单元测试
 * @CreateDate: 22:57 2020/10/21
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void findOne() {

        Integer categoryId = 1;
        ProductCategory productCategory = productCategoryService.findOne(categoryId);
        log.info("{}", productCategory.toString());

    }

    @Test
    public void findAll() {

        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        Assert.assertNotEquals(0, productCategoryList.size());

    }

    @Test
    public void findByCategoryTypeIn() {

        List<Integer> list = Arrays.asList(100001, 200002);
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, productCategoryList.size());

    }

    @Test
    public void save() {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("汉堡");
        productCategory.setCategoryType(200008);
        ProductCategory result = productCategoryService.save(productCategory);
        Assert.assertNotNull(result);

    }
}
