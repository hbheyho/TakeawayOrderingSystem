package com.project.sell.service.impl;

import com.project.sell.dataobject.ProductInfo;
import com.project.sell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: HB
 * @Description: ProductInfoServiceImpl 单元测试
 * @CreateDate: 14:51 2020/10/24
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void findOne() {

        String productId = "121212";
        ProductInfo productInfo = productInfoService.findOne(productId);
        log.info("{}", productInfo.toString());
        Assert.assertEquals("121212", productInfo.getProductId());

    }

    @Test
    public void findUpAll() {

        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());

    }

    @Test
    public void findAll() {

        // 构造分页条件
        PageRequest pageRequest = new PageRequest(1,2);
        Page<ProductInfo> productInfoList = productInfoService.findAll(pageRequest);
        log.info("{}", productInfoList.getTotalElements());

    }

    @Test
    public void save() {

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("121216");
        productInfo.setProductName("汉堡");
        productInfo.setProductPrice(new BigDecimal("31.2"));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很合");
        productInfo.setProductIcon("http://hhhh");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(200001);

        ProductInfo productInfo1 = productInfoService.save(productInfo);
        Assert.assertNotNull(productInfo1);

    }

}