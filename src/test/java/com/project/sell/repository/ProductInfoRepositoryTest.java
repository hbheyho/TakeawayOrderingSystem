package com.project.sell.repository;

import com.project.sell.dataobject.ProductInfo;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: HB
 * @Description: 商品相关测试类
 * @CreateDate: 14:26 2020/10/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;


    @Test
    public void testSave() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("121212");
        productInfo.setProductName("汉堡");
        productInfo.setProductPrice(new BigDecimal("3.2"));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很合");
        productInfo.setProductIcon("http://hhhh");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(200001);

        ProductInfo productInfo1 = productInfoRepository.save(productInfo);
        Assert.assertNotNull(productInfo1);
    }

    @Test
    public void testFindByProductStatus() {
        Integer productStatus = 0;
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatus(productStatus);

        Assert.assertNotEquals(0, productInfoList.size());
    }
}