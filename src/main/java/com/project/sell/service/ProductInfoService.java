package com.project.sell.service;

import com.project.sell.dataobject.ProductCategory;
import com.project.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: HB
 * @Description: ProductInfo service 接口类
 * @CreateDate: 22:48 2020/10/21
 */

public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /* 查询所有在架商品 */
    List<ProductInfo> findUpAll();

    /* 查询所有结果并分页 */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    // todo 加库存

    // todo 减库存

}
