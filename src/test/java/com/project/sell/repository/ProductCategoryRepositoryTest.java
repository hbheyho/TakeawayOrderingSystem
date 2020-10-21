package com.project.sell.repository;

import com.project.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


/**
 * @Author: HB
 * @Description: ProductCategory单元测试
 * @CreateDate: 21:30 2020/10/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /* 查询时间 */
    @Test
    public void getData() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        System.out.println(productCategory.toString());
    }

    /* 新增数据 */
    @Test
    // 该注解会完成去回滚操作, 保证测试之后数据库数据的干净
    @Transactional
    public void saveData() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("汉堡");
        productCategory.setCategoryType(200008);
        ProductCategory result = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(result);
    }

    /* 更新数据 */
    @Test
    public void updateData() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        productCategory.setCategoryName("汉堡");
        productCategoryRepository.save(productCategory);
    }

    /* 查找类目数据 */
    @Test
    public void findCategoryList() {
        List<Integer> list = Arrays.asList(100001, 200001);
        List<ProductCategory> productCategoryList = productCategoryRepository.findByCategoryTypeIn(list);
        for (ProductCategory productCategory :
                productCategoryList) {
            System.out.println(productCategory.toString());
        }
        Assert.assertNotEquals(0, productCategoryList);
    }
}